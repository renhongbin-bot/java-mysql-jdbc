package com.bin.lesson03;

import com.bin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
    public static void main(String[] args) throws SQLException {
        login("'or ' 1=1", "'or ' 1=1");
    }
    public static void login(String username, String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection(); //获取连接数据
            statement = connection.createStatement(); //获取SQL的执行对象
            String sql = "SELECT * FROM `t_user` WHERE `userName`='" + username + "'AND `userPassword`='" + password + "'";
            resultSet = statement.executeQuery(sql); //查询完毕返回结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("userName"));
                System.out.println(resultSet.getString("userPassword"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(connection, statement, resultSet);
        }
    }
}
