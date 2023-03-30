package com.bin.lesson02.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Collections;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    static {
        try {
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            //1. 驱动只用加载一次
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    2.获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
//    3.释放连接
    public static void release(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if(resultSet != null ) {
            resultSet.close();
        }
        if(statement != null) {
            statement.close();
        }
        if(connection != null) {
            connection.close();
        }
    }
}
