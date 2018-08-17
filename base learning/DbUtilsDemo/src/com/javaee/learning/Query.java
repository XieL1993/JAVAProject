package com.javaee.learning;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class Query {
    public static void main(String[] args) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner runner = new QueryRunner(dataSource);
        try {
            List<Account> accounts = runner.query("select * from account where name = ?", new BeanListHandler<>(Account.class), "郑爽");
            for (Account account : accounts) {
                System.out.println(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
