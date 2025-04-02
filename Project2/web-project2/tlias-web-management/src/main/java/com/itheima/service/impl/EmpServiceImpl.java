package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> list = empMapper.list(empQueryParam);
        //3.解析查询结果
        Page<Emp> pageList = (Page<Emp>) list;
        return new PageResult<Emp>(pageList.getTotal(),pageList.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.save(emp);
            //int i = 1/0;
            if (!CollectionUtils.isEmpty(emp.getExprList())) {
                List<EmpExpr> empList = emp.getExprList();
                empList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                    log.info(String.valueOf(emp.getId()));
                });
                empExprMapper.insertBach(empList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(List<Integer> list){
        empMapper.deleteById(list);
        empExprMapper.deleteByEmpId(list);
    }

    @Override
    public Emp getInfoById(Integer id) {
        return empMapper.getInfoById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        empExprMapper.deleteByEmpId(Arrays.asList(emp.getId()));

        if(!CollectionUtils.isEmpty(emp.getExprList())) {
            List<EmpExpr> empList = emp.getExprList();
            empList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBach(empList);
        }
    }
}
