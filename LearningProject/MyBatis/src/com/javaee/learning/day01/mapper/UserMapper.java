package com.javaee.learning.day01.mapper;

import com.javaee.learning.day01.pojo.User;

import java.util.List;

public interface UserMapper {

    int insertUser(User user);

    User findUserById(int uid);

    List<User> findAll();

    int updateName(User user);

    int deleteUser(int uid);

    List<User> getUserByName(String name);
}
