package com.itheima.springbootweb01.service.impl;

import com.itheima.springbootweb01.dao.impl.UserDaoImpl;
import com.itheima.springbootweb01.pojo.User;
import com.itheima.springbootweb01.service.UserService;
import com.itheima.springbootweb01.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> findAll(){
        //读取用户信息文件
        List<String> lines = userDao.getList();
        //解析文件内容
        List<User> userList = lines.stream().map(line->{
            String[] split = line.split(",");
            Integer id = Integer.parseInt(split[0]);
            String username = split[1];
            String password = split[2];
            String name = split[3];
            Integer age = Integer.parseInt(split[4]);
            LocalDateTime updateTime = LocalDateTime.parse(split[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new User(id, username, password, name, age, updateTime);
        }).collect(Collectors.toList());
        return userList;
    }
}

