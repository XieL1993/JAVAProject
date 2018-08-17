package com.javaee.learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPool {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        MyDataSource dataSource = new MyDataSource();
        try {
            conn = dataSource.getConnection();
            String sql = "insert into account values (null , 'zhengshuang' , 100)";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(conn, ps);
        }
    }
}
