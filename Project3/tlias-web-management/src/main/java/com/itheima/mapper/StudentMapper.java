package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> getStudents(StudentQueryParam queryString);

    void deleteStudentByIds(List<Integer> ids);

    void insertStudent(Student student);

    Student selectStudentById(Integer id);

    void updateStudent(Student student);
}
