package com.jd.mobile.dao;

import com.jd.mobile.domin.Order;
import com.jd.mobile.domin.OrderItem;
import com.jd.mobile.domin.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void saveOrder(Order order) throws SQLException;

    void deleteOrder(Order order) throws SQLException;

    void saveOrderItem(OrderItem item) throws SQLException;

    void deleteOrderItem(OrderItem item) throws SQLException;

    int getOrderCount(User user) throws SQLException;

    List<Order> getOrderList(User user, int pageSize, int currentPage) throws SQLException;

    Order getOrderDetail(String oid) throws SQLException;

    void updateOrder(Order order) throws SQLException;
}
