package com.javaee.learning.service;

import com.javaee.learning.dao.Company;
import com.javaee.learning.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserService {
    private static String resource = "applicationContext.xml";

    public static void main(String[] args) {
        test6();
    }

    public static void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        UserDao userDao = (UserDao) context.getBean("UserDao");
        userDao.add();
        ((ClassPathXmlApplicationContext) context).close();
    }

    public static void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        Company company = (Company) context.getBean("Company");
        company.read();
    }
    public static void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.add();
        ((ClassPathXmlApplicationContext) context).close();
    }
    public static void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        Company company = (Company) context.getBean("company");
        company.read();
    }
    public static void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        UserDao userDao = (UserDao) context.getBean("user");
        userDao.add();
    }
    public static void test6() {
        ApplicationContext context = new ClassPathXmlApplicationContext(resource);
        Company company = (Company) context.getBean("company2");
        company.read();
    }
}
