package com.javaee.learning.JDKProxy;

import org.junit.Test;

public class Demo1 {
    @Test
    public void test1() {
        UserDao dao = new UserDaoImpl();
        UserDao proxy = new JDKProxy(dao).createProxy();
        proxy.save();
        proxy.update();
        proxy.find();
        proxy.delete();
    }
}
