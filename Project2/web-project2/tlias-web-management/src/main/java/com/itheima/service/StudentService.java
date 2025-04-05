package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    public PageResult<Student> getStudents(StudentQueryParam queryString);

    void deleteStudentByIds(List<Integer> ids);

    void insertStudent(Student student);

    Student selectStudentById(Integer id);

    void updateStudent(Student student);

    void updateViolationById(Integer id, Integer score);
}
