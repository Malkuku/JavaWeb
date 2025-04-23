package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateViolationById(Integer id, Integer score) {
        Student student = studentMapper.selectStudentById(id);
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationScore((short) (student.getViolationScore()+score));
        student.setViolationCount((short) (student.getViolationCount()+1));
        studentMapper.updateStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }

    @Override
    public Student selectStudentById(Integer id) {
        log.info("id:{}", id);
        return studentMapper.selectStudentById(id);
    }

    @Override
    public void insertStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insertStudent(student);
    }

    @Override
    public void deleteStudentByIds(List<Integer> ids) {
        studentMapper.deleteStudentByIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PageResult<Student> getStudents(StudentQueryParam queryString) {
        PageHelper.startPage(queryString.getPage(),queryString.getPageSize());

        List<Student> rows = studentMapper.getStudents(queryString);
        Page<Student> p = (Page<Student>) rows;

        if(!rows.isEmpty()){
            rows.forEach(row->{
                row.setCreateTime(LocalDateTime.now());
                row.setUpdateTime(LocalDateTime.now());
            });
        }

        return new PageResult<>(p.getTotal(),p.getResult());
    }
}
