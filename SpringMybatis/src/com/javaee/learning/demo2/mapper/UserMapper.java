package com.javaee.learning.demo2.mapper;


import com.javaee.learning.demo2.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

}
