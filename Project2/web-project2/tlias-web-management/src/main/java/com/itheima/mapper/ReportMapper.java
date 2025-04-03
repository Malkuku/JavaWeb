package com.itheima.mapper;

import com.itheima.pojo.JobOption;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {

    @MapKey("pos")
    List<Map<String,Object>> getEmpJobData();

    @MapKey("name")
    List<Map<String,Object>> getEmpGenderData();
}
