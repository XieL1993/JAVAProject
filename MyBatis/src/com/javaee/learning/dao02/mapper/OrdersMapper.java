package com.javaee.learning.dao02.mapper;

import com.javaee.learning.dao02.pojo.Orders;

import java.util.List;

public interface OrdersMapper {

    int insertOrder(Orders order);

    List<Orders> findAll();
}
