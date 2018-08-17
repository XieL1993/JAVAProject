package com.javaee.c3p0;

import com.javaee.learning.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class C3p0Demo {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            connection = dataSource.getConnection();
            String sql = "insert into account values (null , ? , ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"唐嫣");
            statement.setInt(2,300);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,statement);
        }
    }
}
