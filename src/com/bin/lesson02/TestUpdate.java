package com.bin.lesson02;

import com.bin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection(); //获取连接数据
            statement = connection.createStatement(); //获取SQL的执行对象
            String sql = "UPDATE t_class SET `classContent`='后端技术' WHERE `classId`=2";
            int i = statement.executeUpdate(sql);
            if(i > 0) {
                System.out.println("更新成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(connection, statement, resultSet);
        }
    }
}
