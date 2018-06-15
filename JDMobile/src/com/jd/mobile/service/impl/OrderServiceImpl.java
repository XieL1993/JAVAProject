package com.jd.mobile.service.impl;

import com.jd.mobile.dao.OrderDao;
import com.jd.mobile.dao.impl.OrderDaoImpl;
import com.jd.mobile.domin.Order;
import com.jd.mobile.domin.OrderItem;
import com.jd.mobile.domin.PageBean;
import com.jd.mobile.domin.User;
import com.jd.mobile.service.OrderService;
import com.jd.mobile.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao dao = new OrderDaoImpl();

    @Override
    public void saveOrder(Order order) throws Exception {
        try {
            JDBCUtils.startTransaction();
            dao.saveOrder(order);
            List<OrderItem> list = order.getList();
            for (OrderItem item : list) {
                dao.saveOrderItem(item);
            }
            JDBCUtils.commitTransaction();
        } catch (SQLException e) {
            JDBCUtils.rollbackTransaction();
            throw new SQLException(e.getMessage());
        } finally {
            JDBCUtils.closeConnection();
        }
    }

    @Override
    public void deleteOrder(Order order) throws Exception {
        try {
            JDBCUtils.startTransaction();
            List<OrderItem> list = order.getList();
            for (OrderItem item : list) {
                dao.deleteOrderItem(item);
            }
            dao.deleteOrder(order);
            JDBCUtils.commitTransaction();
        } catch (SQLException e) {
            JDBCUtils.rollbackTransaction();
            throw new SQLException(e.getMessage());
        } finally {
            JDBCUtils.closeConnection();
        }
    }

    @Override
    public PageBean<Order> getOrderList(User user, int pageSize, int currentPage) throws SQLException {
        PageBean<Order> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        int count = dao.getOrderCount(user);
        pageBean.setTotalSize(count);
        pageBean.setTotalPage(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        List<Order> list = dao.getOrderList(user, pageSize, currentPage);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public Order getOrderDetail(String oid) throws SQLException {
        return dao.getOrderDetail(oid);
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        dao.updateOrder(order);
    }
}
