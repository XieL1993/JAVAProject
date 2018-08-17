package com.javaee.learning.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDemo2 {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;

    @Test
    public void demo1() {
        template.update("INSERT into account values(null,?,?)", "刘亦菲", 9999);
    }

    @Test
    public void demo2() {
        template.update("UPDATE account set money = ? where name = ?", 666666, "郑爽");
    }

    @Test
    public void delete() {
        template.update("DELETE from account where id = ?", 4);
    }

    @Test
    public void find() {
        Account account = template.queryForObject("select * from account where id = ?", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account obj = new Account();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setMoney(resultSet.getDouble("money"));
                return obj;
            }
        }, 2);
        System.out.println(account);
    }

    @Test
    public void findAll() {
        List<Account> accounts = template.query("select * from account", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setName(resultSet.getString("name"));
                account.setMoney(resultSet.getDouble("money"));
                return account;
            }
        });
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
