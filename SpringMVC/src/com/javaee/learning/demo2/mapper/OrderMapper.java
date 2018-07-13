package com.javaee.learning.demo2.mapper;

import com.javaee.learning.demo2.pojo.Orders;

import java.util.List;

public interface OrderMapper {
    List<Orders> findAll();

    Orders findById(int oid);

    int updateOrder(Orders order);
}
