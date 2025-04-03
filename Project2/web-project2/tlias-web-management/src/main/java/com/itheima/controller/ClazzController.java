package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    //查询班级列表
    @RequestMapping(method = RequestMethod.GET)
    public Result getClazzs(ClazzQueryParam queryString) {
        PageResult<Clazz> PageResult = clazzService.getClazzs(queryString);
        return Result.success(PageResult);
    }

    //删除班级
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        log.info("delete:{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    //添加班级
    @RequestMapping(method = RequestMethod.POST)
    public Result insertClazz(@RequestBody Clazz clazz) {
        log.info("insert:{}",clazz);
        clazzService.insertClazz(clazz);
        return Result.success();
    }

    //根据id查询
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result getClazzById(@PathVariable Integer id) {
        log.info("get:{}",id);
        Clazz clazz = clazzService.getClazzById(id);
        log.info("get:{}",clazz);
        return Result.success(clazz);
    }

    //修改班级
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("update:{}",clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    //查询所有班级
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getClazzList(){
        List<Clazz> clazzList = clazzService.getClazzList();
        log.info("list:{}",clazzList);
        return Result.success(clazzList);
    }
}
