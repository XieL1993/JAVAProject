package com.javaee.learning.demo3;

public interface AccountDao {
    void outMoney(String from, Double money);

    void inMoney(String to, Double money);
}
