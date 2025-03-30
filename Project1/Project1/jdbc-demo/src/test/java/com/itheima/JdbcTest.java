package com.itheima;

import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JdbcTest {
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/web01";
        String username = "root";
        String password = "pin666";
        Connection connection = DriverManager.getConnection(url, username, password);
        //3.创建语句对象
        Statement statement = connection.createStatement();
        //4.执行sql语句
        int i = statement.executeUpdate("update user set age = 25 where id = 1");
        System.out.printf("影响了%d行数据", i);

        //5.释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testSelect(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            // 1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 获取连接
            String url = "jdbc:mysql://localhost:3306/web01";
            String username = "root";
            String password = "pin666";
            connection = DriverManager.getConnection(url, username, password);

            // 4. 执行SQL查询
            String sql = "select id, username, password, name, age from user where username = ? and password = ?";
            // 使用PreparedStatement防止SQL注入
            statement = connection.prepareStatement(sql);
            statement.setString(1, "daqiao");
            statement.setString(2, "123456");

            resultSet = statement.executeQuery();

            // 5. 处理结果集
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")
                );

                // 6. 输出User对象的数据
                System.out.println(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 7. 释放资源
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


