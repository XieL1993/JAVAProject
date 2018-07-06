package com.javaee.learning.demo2;

public interface AccountDao {
    void outMoney(String from, Double money);

    void inMoney(String to, Double money);
}
