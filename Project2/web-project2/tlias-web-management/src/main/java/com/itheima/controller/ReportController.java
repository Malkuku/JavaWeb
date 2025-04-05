package com.itheima.controller;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "/empJobData",method = RequestMethod.GET)
    public Result getEmpJobData(){
        JobOption<Object> jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @RequestMapping(value = "/empGenderData",method = RequestMethod.GET)
    public Result getEmpGenderData(){
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    @RequestMapping(value = "/studentDegreeData",method = RequestMethod.GET)
    public Result getStudentDegreeData(){
        List<Map<String,Object>> degreeList = reportService.getStudentDegreeData();
        log.info("degreeList:{}", degreeList);
        return Result.success(degreeList);
    }

    @RequestMapping(value = "/studentCountData",method = RequestMethod.GET)
    public Result getStudentCountData(){
        ClazzOption countData = reportService.getStudentCountData();
        log.info("countData:{}", countData);
        return Result.success(countData);
    }
}
