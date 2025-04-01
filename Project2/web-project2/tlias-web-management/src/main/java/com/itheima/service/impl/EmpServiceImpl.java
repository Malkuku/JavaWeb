package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

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

    @Override
    public void save(Emp emp) {
         emp.setCreateTime(LocalDateTime.now());
         emp.setUpdateTime(LocalDateTime.now());
         empMapper.save(emp);

         if(!CollectionUtils.isEmpty(emp.getExprList())){
             List<EmpExpr> empList = emp.getExprList();
             empList.forEach(empExpr -> {
                 empExpr.setEmpId(emp.getId());
                 log.info(String.valueOf(emp.getId()));
             });
             empExprMapper.insertBach(empList);
         }
    }

}
