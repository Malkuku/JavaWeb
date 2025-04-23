package com.itheima.service.impl;

import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> getClazzList() {
        return clazzMapper.getClazzList();
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        return clazzMapper.getClazzById(id);
    }

    //查询列表
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageResult<Clazz> getClazzs(ClazzQueryParam queryString){
        //获取总数
        Long total = clazzMapper.counts();
        Integer start = (queryString.getPage() - 1) * queryString.getPageSize();
        //获取rows
        List<Clazz> rows = clazzMapper.selectClazzs(start,queryString.getPageSize());
        //处理status
        if(!rows.isEmpty()){
            rows.forEach(row->{
                if(row.getBeginDate().isAfter(LocalDateTime.now().toLocalDate()))
                    row.setStatus("未开班");
                if(row.getEndDate().isBefore(LocalDateTime.now().toLocalDate()))
                    row.setStatus("已结课");
                else row.setStatus("已开班");
            });
        }

        return new PageResult<Clazz>(total,rows);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("deleteById:{}",id);
        clazzMapper.deleteClazzs(id);
    }

    @Override
    public void insertClazz(Clazz clazz) {
        log.info("insert:{}",clazz);
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateTime(LocalDateTime.now());
        clazzMapper.insertClazz(clazz);
    }
}
