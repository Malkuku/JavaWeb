package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //自动创建代理对象（实现类），并将其放入IOC容器（bean）中
public interface UserMapper {
    //@Select("select * from user")
    List<User> findAll(); //查询所有用户信息

    @Delete("delete from user where id = #{id}")
    void deleteById(Integer id); //根据id删除用户信息 ，Mybatis会自动将参数传入到#{id}中

    @Insert("insert into user(username,password,name,age) values (#{username},#{password},#{name},#{age})")
    void insertUser(User user); //添加用户信息

    @Update("update user set username = #{username},password = #{password},name = #{name},age = #{age} where id = #{id}")
    void updateUser(User user); //修改用户信息

    @Select("select * from user where username = #{username} and password = #{password}")
    List<User> findAllByUsernameAndPassword(@Param("username")String username,@Param("password")String password); //根据用户名和密码查询用户信息
}

