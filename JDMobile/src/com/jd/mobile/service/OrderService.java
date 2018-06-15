package com.jd.mobile.service;

import com.jd.mobile.domin.Order;
import com.jd.mobile.domin.PageBean;
import com.jd.mobile.domin.User;

import java.sql.SQLException;

public interface OrderService {
    void saveOrder(Order order) throws Exception;

    void deleteOrder(Order order) throws Exception;

    PageBean<Order> getOrderList(User user, int pageSize, int currentPage) throws SQLException;

    Order getOrderDetail(String oid) throws SQLException;

    void updateOrder(Order order) throws SQLException;
}
