package com.jd.mobile.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static ComboPooledDataSource ds = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    public static DataSource getDataSource() {
        return ds;
    }


    public static Connection getConnection() throws SQLException {
        try {
            //得到当前线程上绑定的连接
            Connection conn = tl.get();
            if (conn == null) {  //代表线程上没有绑定连接
                conn = ds.getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startTransaction() {
        try {
            //得到当前线程上绑定连接开启事务
            Connection conn = tl.get();
            if (conn == null) {  //代表线程上没有绑定连接
                conn = ds.getConnection();    //ds 代表数据源， 这里省略他的代码
                tl.set(conn);
            }
            conn.setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void commitTransaction() {
        try {
            Connection conn = tl.get();
            if (conn != null) {
                conn.commit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollbackTransaction() {
        try {
            Connection conn = tl.get();
            if (conn != null) {
                conn.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            Connection conn = tl.get();
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            tl.remove();   //解除当前线程上绑定的链接（从ThreadLocal容器中移除对应当前线程的链接）
        }
    }
}
