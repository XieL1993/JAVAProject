package com.javaee.learning.demo2.service;

import com.javaee.learning.demo2.mapper.OrderMapper;
import com.javaee.learning.demo2.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
//@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<Orders> findAll() {
        List<Orders> orders = orderMapper.findAll();

        for (Orders order : orders) {
            System.out.println(order);
        }
        return orders;
    }

    public Orders findByid(int oid) {
        Orders order = orderMapper.findById(oid);
        System.out.println(order);
        return order;
    }

    public int updateOrder(Orders order) {
        return orderMapper.updateOrder(order);
    }
}
