package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    void save(Emp emp);


    void deleteById(List<Integer> list);

    Emp getInfoById(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
