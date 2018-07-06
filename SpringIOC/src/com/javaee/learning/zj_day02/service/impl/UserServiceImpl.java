package com.javaee.learning.zj_day02.service.impl;

import com.javaee.learning.zj_day02.dao.UserDao;
import com.javaee.learning.zj_day02.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao dao;

    @PostConstruct
    public void init() {
        System.out.println("service init");
    }

    @PreDestroy
    public void destory() {
        System.out.println("service destory");
    }

    @Override
    public void say() {
        System.out.println("service---用户");
        dao.say();
    }
}
