package com.javaee.learning.demo1.mapper;


import com.javaee.learning.demo1.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

}
