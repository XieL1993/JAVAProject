package com.javaee.dpcp;

import com.javaee.learning.JDBCUtils;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class DpcpDemo {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Properties properties = new Properties();
            InputStream is = new FileInputStream("dpcp.properties");
            properties.load(is);
            DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            String sql = "insert into account values (null , ? , ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"yangmi");
            statement.setInt(2,200);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(connection,statement);
        }

    }
}
