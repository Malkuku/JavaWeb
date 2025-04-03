package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> getClazzs(@RequestParam ClazzQueryParam queryString);

    void deleteById(@PathVariable Integer id);

    void insertClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getClazzList();
}
