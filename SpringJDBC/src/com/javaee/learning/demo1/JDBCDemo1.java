package com.javaee.learning.demo1;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBCDemo1 {
    @Test
    public void demo1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/springDemo");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
        template.update("INSERT into account values(null,?,?)", "郑爽", 8888);
    }
}
