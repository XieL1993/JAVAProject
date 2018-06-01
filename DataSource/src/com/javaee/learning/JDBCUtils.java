package com.javaee.learning;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    static String driverClass;
    static String url;
    static String name;
    static String password;

    static {
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("jdbc.properties");
            properties.load(is);
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(Connection conn, Statement st, ResultSet rs) {
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }

    public static void release(Connection conn, Statement st) {
        closeSt(st);
        closeConn(conn);
    }

    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }
    }

    private static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st = null;
        }
    }

    private static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
