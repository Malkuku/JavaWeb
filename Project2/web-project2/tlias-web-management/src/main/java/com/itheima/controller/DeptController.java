package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    public Result list() {
        log.info("查询所有部门");
        List<Dept> deptList = deptService.findAll();
        if(deptList != null && deptList.size() > 0) {
            return Result.success(deptList);
        }else{
            return Result.error("没有部门数据");
        }
    }

    /**
     * 根据id删除部门
     */
//根据HttpServletRequest获取请求参数
    //@RequestMapping(value = "/depts",method = RequestMethod.DELETE)
    //public Result delete(HttpServletRequest request){
    //    String idStr = request.getParameter("id");
    //    int id = Integer.parseInt(idStr);
    //    log.info(id);
    //    return Result.success();
    //}

    //通过RequestParam注解来获取
    @RequestMapping(value = "/depts",method = RequestMethod.DELETE)
    public Result deleteById(@RequestParam("id") Integer id){
        log.info(String.valueOf(id));
        return Result.success();
    }

    //形参名与请求参数名保持一致
    @RequestMapping(value = "/depts",method = RequestMethod.DELETE)
    public Result delete(Integer id) {
        log.info(String.valueOf(id));
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加部门
     */
    @RequestMapping(value = "/depts",method = RequestMethod.POST)
    public Result addDept(@RequestBody Dept dept) {
        log.info("增加部门{}",dept);
        deptService.addDept(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门数据并返回
     */
    @RequestMapping(value = "/depts/{id}",method = RequestMethod.GET)
    public Result selectById(@PathVariable("id") Integer id) {
        log.info(String.valueOf(id));
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     */
    @RequestMapping(value = "/depts",method = RequestMethod.PUT)
    public Result updateDept(@RequestBody Dept dept) {
        log.info(String.valueOf(dept));
        deptService.updateDept(dept);
        return Result.success();
    }
}
