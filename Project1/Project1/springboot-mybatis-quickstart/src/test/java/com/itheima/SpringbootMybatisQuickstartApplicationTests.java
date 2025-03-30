package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import java.util.List;

@SpringBootTest //标记当前类是一个SpringBoot测试类，当前类会自动加载SpringBoot的上下文环境
class SpringbootMybatisQuickstartApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> userList =  userMapper.findAll();
        userList.forEach(System.out::println);
    }

    /**
     * 测试删除用户信息
     */
    @Test
    public void testDeleteById(){
        userMapper.deleteById(1);
        System.out.println("删除成功");
    }

    /**
     * 测试添加用户信息
     */
    @Test
    public void testInsertUser(){
        User user = new User(null,"heyi", "123456", "heyi", 18);
        userMapper.insertUser(user);
    }

    /**
     * 测试修改用户信息
     */
    @Test
    public void testUpdateUser(){
        User user = new User(2,"heyi", "123456", "heyi", 18);
        userMapper.updateUser(user);
    }

    /**
     * 测试根据用户名和密码查询用户信息
     */
    @Test
    public void testFindUserByUsernameAndPassword(){
        List<User> userList = userMapper.findAllByUsernameAndPassword("heyi","123456");
        userList.forEach(System.out::println);
    }
}
