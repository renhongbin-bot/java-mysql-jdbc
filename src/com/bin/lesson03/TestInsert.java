package com.bin.lesson03;

import com.bin.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = JdbcUtils.getConnection();
            String sql = "INSERT INTO t_class(classId, `className`, `classContent`) VALUES(?, ?, ?)";
            st = connection.prepareStatement(sql);
            st.setInt(1, 4);
            st.setString(2, "网络");
            st.setString(3, "网络技术");
            int i = st.executeUpdate();
            if(i > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.release(connection,st,null);
        }
    }
}
