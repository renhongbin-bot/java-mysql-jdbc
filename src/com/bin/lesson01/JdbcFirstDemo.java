package com.bin.lesson01;

import java.sql.*;

//我的第一个JDBC程序
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver"); //固定写法
        //2.用户信息和url
        // serverTimezone=GMT%2B8 设置时区为东八区
        //useUnicode=true 支持中文编码
        //characterEncoding=utf8 设置中文字符集为utf8
        // useSSL=false 使用安全的连接
        String url = "jdbc:mysql://localhost:3306/blog?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false";
        String username = "root";
        String password = "123456";
        //3.连接成功.获取到数据库对象
        Connection connection = DriverManager.getConnection(url, username, password);

        //4.执行SQL对象 statement:执行sql的对象
        Statement statement = connection.createStatement();
        //5. 执行SQL的对象去执行SQL,可能存在的结果,查看返回结果
        String sql = "SELECT * FROM t_user where userId=1";
        ResultSet resultSet = statement.executeQuery(sql); //返回的结果集
        while (resultSet.next()) {
            System.out.println("userId=" + resultSet.getObject("userId"));
            System.out.println("userName=" + resultSet.getObject("userName"));
            System.out.println("userNickName=" + resultSet.getObject("userNickName"));
            System.out.println("userPassword" + resultSet.getObject("userPassword"));
        }
        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
