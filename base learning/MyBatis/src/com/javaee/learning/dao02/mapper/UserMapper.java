package com.javaee.learning.dao02.mapper;


import com.javaee.learning.dao02.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insertUser(User user);

    List<User> findAll(@Param(value = "name") String name, @Param(value = "password") String password);

    List<User> findUserByIds(@Param(value = "ids") Integer[] args);

    List<User> getUserAndOrders();
}
