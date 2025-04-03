package com.itheima.service.impl;

import com.itheima.mapper.ReportMapper;
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
