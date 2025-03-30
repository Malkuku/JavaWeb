package com.itheima.springbootweb01.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.springbootweb01.dao.UserDao;
import com.itheima.springbootweb01.pojo.User;
import com.itheima.springbootweb01.service.UserService;
import com.itheima.springbootweb01.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


@RestController
//用户信息的控制器
public class UserController {

    private UserService userService;
    //setter注入

    @Resource(name = "userServiceImpl")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    //获取用户信息列表
    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {
        //读取用户信息文件
        List<User> userList = userService.findAll();
        //返回用户信息（json）
        return userList;
    }
}