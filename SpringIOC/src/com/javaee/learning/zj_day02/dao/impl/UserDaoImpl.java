package com.javaee.learning.zj_day02.dao.impl;

import com.javaee.learning.zj_day02.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Value("郑爽")
    private String name;
    @Value("18")
    private String age;

    @Override
    public void say() {
        System.out.println("my name is " + name + ",and my age is " + age);
    }
}
