package com.itheima.service;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门
     */
    List<Dept> findAll();

    /**
     * 根据id删除部门
     */
    void deleteById(Integer id);

    /**
     * 添加部门
     */
    void addDept(Dept dept);

    /**
     * id查找
     */
    Dept getInfo(Integer id);

    /**
     * 修改部门
     */
    void updateDept(Dept dept);
}
