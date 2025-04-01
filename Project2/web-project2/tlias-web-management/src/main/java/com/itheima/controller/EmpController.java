package com.itheima.controller;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public Result page (EmpQueryParam empQueryParam) {
        log.info("page:{} rows:{}",empQueryParam.getPage(),empQueryParam.getPageSize());
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @RequestMapping(value = "/emps",method = RequestMethod.POST)
    public Result save(@RequestBody Emp emp) {
        log.info("save:{}",emp);
        empService.save(emp);
        return Result.success();
    }

}
