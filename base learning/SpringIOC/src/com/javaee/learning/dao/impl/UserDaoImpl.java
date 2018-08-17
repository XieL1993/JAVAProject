package com.javaee.learning.dao.impl;

import com.javaee.learning.dao.UserDao;

public class UserDaoImpl implements UserDao {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void created(){
        System.out.println("初始化...");
    }
    public void destroyed(){
        System.out.println("销毁...");
    }
    @Override
    public void add() {
        System.out.println("新增用户:" + name);
    }
}
