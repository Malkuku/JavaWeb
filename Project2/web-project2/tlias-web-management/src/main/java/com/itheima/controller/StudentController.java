package com.itheima.controller;

import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Result getStudents(StudentQueryParam queryString) {
        PageResult<Student> pageResult = studentService.getStudents(queryString);
        log.info("pageResult:{}", pageResult);
        return Result.success(pageResult);
    }

    @RequestMapping(value = "/{ids}",method = RequestMethod.DELETE)
    public Result deleteStudentByIds(@PathVariable List<Integer> ids) {
        log.info("ids:{}", ids);
        studentService.deleteStudentByIds(ids);
        return Result.success();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result insertStudent(@RequestBody Student student) {
        log.info("student:{}", student);
        studentService.insertStudent(student);
        return Result.success();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result selectStudentById(@PathVariable Integer id) {
        log.info("id:{}", id);
        Student student = studentService.selectStudentById(id);
        return Result.success(student);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result updateStudent(@RequestBody Student student) {
        log.info("student:{}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    @RequestMapping(value = "/violation/{id}/{score}", method = RequestMethod.PUT)
    public Result updateViolationById(@PathVariable Integer id,@PathVariable Integer score) {
        log.info("id:{},score:{}", id, score);
        studentService.updateViolationById(id,score);
        return Result.success();
    }
}
