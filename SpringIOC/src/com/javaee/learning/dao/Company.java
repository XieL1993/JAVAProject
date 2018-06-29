package com.javaee.learning.dao;

import com.javaee.learning.dao.impl.UserDaoImpl;

public class Company {
    private UserDaoImpl userDao;
    private String name;

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void read() {
        System.out.println(name + "|" + userDao.getName());
    }
}
