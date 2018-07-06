package com.javaee.learning.demo1.dao;

import com.javaee.learning.demo1.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
