package com.javaee.learning.demo3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class SpringTest {
    @Resource(name = "accountService")
    AccountService service;

    @Test
    public void a() {
        service.transfer("郑爽", "杨幂", 1000d);
    }
}
