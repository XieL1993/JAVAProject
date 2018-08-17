package com.javaee.learning.zj_day02;

import com.javaee.learning.zj_day02.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    @Test
    public void test1() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_day02.xml");
        UserService service = (UserService) context.getBean("userService");
        service.say();
        context.close();
    }
}
