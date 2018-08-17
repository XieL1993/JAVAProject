package com.javaee.learning;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner runner = new QueryRunner(dataSource);
        try {
            runner.update("insert into account values (null , ? , ?)", "郑爽", 400);
            runner.update("delete from account where id = ?", 1);
            runner.update("update account set money = 1000 where name = ?", "郑爽");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
