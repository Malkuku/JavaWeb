package com.itheima.service.impl;

import com.itheima.mapper.ReportMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public ClazzOption getStudentCountData(){
        List<Map<String,Object>> list = reportMapper.getStudentCountData();
        log.info(list.toString());
        List<Object> clazzList = list.stream().map(m->m.get("name").toString()).collect(Collectors.toList());
        List<Object> dataList = list.stream().map(m->m.get("value").toString()).collect(Collectors.toList());
        log.info("clazzList:{}", clazzList);
        log.info("dataList:{}", dataList);
        return new ClazzOption(clazzList,dataList);
    }

    @Override
    public List<Map<String,Object>> getStudentDegreeData(){
        List<Map<String,Object>> list = reportMapper.getStudentDegreeData();
        log.info("list:{}", list);
        return list;
    }

    @Override
    public JobOption<Object> getEmpJobData() {
        List<Map<String,Object>> list = reportMapper.getEmpJobData();
        List<Object> jobList = list.stream().map(obj -> obj.get("pos")).toList();
        List<Object> optionList = list.stream().map(obj -> obj.get("num")).toList();
        log.info(jobList.toString());
        log.info(optionList.toString());
        JobOption<Object> jobOption = new JobOption<>(jobList, optionList);
        log.info(jobOption.getJobList().toString());
        log.info(jobOption.getDataList().toString());
        return jobOption;
    }

    @Override
    public List<Map<String,Object>> getEmpGenderData() {
        return reportMapper.getEmpGenderData();
    }
}
