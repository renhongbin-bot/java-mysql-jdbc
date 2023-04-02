package com.bin.lesson04;

import com.bin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTeansaction1 {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
//            1.关闭数据库自动提交功能,自动开启事务
            conn.setAutoCommit(false);
//           2.执行业务
            String sql1 = "update student.result set StudentResult = StudentResult + 10 where StudentId=1";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();
            String sql2 = "update student.result set StudentResult = StudentResult - 10 where StudentId=2";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

//            3.业务完毕,提交事务
            conn.commit();
            System.out.println("成功!");

        } catch (SQLException e) {
//            4.如果失败回滚事务
            conn.rollback();
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
