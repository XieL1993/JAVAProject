package com.javaee.learning.demo4;

public interface AccountDao {
    void outMoney(String from, Double money);

    void inMoney(String to, Double money);
}
