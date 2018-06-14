package com.jd.mobile.dao.impl;

import com.jd.mobile.dao.OrderDao;
import com.jd.mobile.domin.Order;
import com.jd.mobile.domin.OrderItem;
import com.jd.mobile.domin.Product;
import com.jd.mobile.domin.User;
import com.jd.mobile.utils.BeanFactory;
import com.jd.mobile.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void saveOrder(Order order) throws SQLException {
        String sql = "insert into orders values ( ?,?,?,?,?,?,?,? )";
        QueryRunner runner = new QueryRunner();
        Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
        runner.update(JDBCUtils.getConnection(), sql, params);
    }

    @Override
    public void saveOrderItem(OrderItem item) throws SQLException {
        QueryRunner runner = new QueryRunner();
        runner.update(JDBCUtils.getConnection(), "insert into orderitem values (?,?,?,?,?)", item.getItemid(), item.getQuantity(), item.getTotal(), item.getProduct().getPid(), item.getOid());
    }

    @Override
    public int getOrderCount(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        Number number = (Number) runner.query("select count(*) from orders where uid = ? ", new ScalarHandler(), user.getUid());
        return number.intValue();
    }

    @Override
    public List<Order> getOrderList(User user, int pageSize, int currentPage) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders where uid = ? limit ? , ?";
        List<Order> orderList = runner.query(sql, new BeanListHandler<>(Order.class), user.getUid(), (currentPage - 1) * pageSize, pageSize);
        for (Order order : orderList) {
            order.setUser(user);
            String oid = order.getOid();
            sql = "select * from orderitem o ,product p where o.pid = p.pid and oid = ? ";
            List<Map<String, Object>> items = runner.query(sql, new MapListHandler(), oid);
            for (Map<String, Object> map : items) {
                OrderItem orderItem = BeanFactory.populateMap(OrderItem.class, map);
                Product product = BeanFactory.populateMap(Product.class, map);
                orderItem.setProduct(product);
                order.getList().add(orderItem);
            }
        }
        return orderList;
    }

    @Override
    public Order getOrderDetail(String oid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from orders where oid = ? ";
        Order order = runner.query(sql, new BeanHandler<>(Order.class), oid);
        sql = "select * from orderitem o ,product p where o.pid = p.pid and oid = ? ";
        List<Map<String, Object>> items = runner.query(sql, new MapListHandler(), oid);
        for (Map<String, Object> map : items) {
            Product product = BeanFactory.populateMap(Product.class, map);
            OrderItem orderItem = BeanFactory.populateMap(OrderItem.class, map);
            orderItem.setProduct(product);
            order.getList().add(orderItem);
        }
        return order;
    }
}
