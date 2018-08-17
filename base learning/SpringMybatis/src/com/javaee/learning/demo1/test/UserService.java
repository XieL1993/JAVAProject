package com.javaee.learning.demo1.test;

import com.javaee.learning.demo1.dao.UserDao;
import com.javaee.learning.demo1.dao.impl.UserDaoImpl;
import com.javaee.learning.demo1.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class UserService {
    private static UserDao userDao;

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userDao = (UserDaoImpl) context.getBean("userDao");
    }

    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
