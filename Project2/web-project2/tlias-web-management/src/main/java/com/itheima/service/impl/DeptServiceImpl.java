package com.itheima.service.impl;

import com.itheima.exception.CustomException;
import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 根据id删除部门
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        //先查询有没有员工
        Integer empCount = deptMapper.selectEmpCountById(id);
        //如果有员工，抛出自定义错误
        if (empCount > 0) {
            throw new CustomException("该部门下有员工，无法删除");
        }

        deptMapper.deleteById(id);
    }

    /**
     * 添加部门
     */
    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    /**
     * id查找部门
     */
    @Override
    public Dept getInfo(Integer id) {
        return deptMapper.selectDeptById(id);
    }

    /**
     * 修改部门
     */
    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
