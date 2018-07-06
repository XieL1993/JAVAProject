package com.javaee.learning.demo2.test;

import com.javaee.learning.demo2.mapper.UserMapper;
import com.javaee.learning.demo2.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserService {

    @Test
    public void findAll() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserMapper mapper = context.getBean(UserMapper.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
