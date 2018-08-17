package com.javaee.learning.CglibProxy;

import org.junit.Test;

public class Demo {
    @Test
    public void test() {
        CustomerDao dao = new CustomerDao();
        CustomerDao proxy = new CglibProxy(dao).createProxy();
        proxy.save();
        proxy.update();
        proxy.find();
        proxy.delete();
    }
}
