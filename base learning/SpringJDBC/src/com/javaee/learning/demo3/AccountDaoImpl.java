package com.javaee.learning.demo3;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate template;

    @Override
    public void outMoney(String from, Double money) {
        template.update("update account set money = money -? where name = ?", money, from);
    }

    @Override
    public void inMoney(String to, Double money) {
        template.update("update account set money=money + ? where name = ?", money, to);
    }
}
