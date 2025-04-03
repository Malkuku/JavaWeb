package com.itheima.mapper;


import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    //查询总计
    Long counts();

    //查询列表
    List<Clazz> selectClazzs(Integer start,Integer pageSize);

    //删除
    void deleteClazzs(Integer id);

    //添加
    void insertClazz(Clazz clazz);

    //通过id查询
    Clazz getClazzById(Integer id);

    //修改
    void updateClazz(Clazz clazz);

    //获取所有班级
    List<Clazz> getClazzList();
}
