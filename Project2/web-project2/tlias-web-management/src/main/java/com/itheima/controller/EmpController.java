package com.itheima.controller;

import com.itheima.mapper.EmpExprMapper;
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
import java.util.List;

@Slf4j
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private EmpExprMapper empExprMapper;

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

    @RequestMapping(value = "/emps",method = RequestMethod.DELETE)
    public Result delete(@RequestParam List<Integer> ids){
        log.info("delete:{}",ids);
        empService.deleteById(ids);
        empExprMapper.deleteByEmpId(ids);
        return Result.success();
    }

    @RequestMapping(value = "/emps/{id}",method = RequestMethod.GET)
    public Result getInfo(@PathVariable Integer id) {
        log.info("getInfo:{}",id);
        Emp emp = empService.getInfoById(id);
        return Result.success(emp);
    }

    @RequestMapping(value = "/emps",method = RequestMethod.PUT)
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }

}
