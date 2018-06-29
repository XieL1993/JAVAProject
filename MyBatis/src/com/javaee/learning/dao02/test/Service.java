package com.javaee.learning.dao02.test;

import com.javaee.learning.dao02.mapper.OrdersMapper;
import com.javaee.learning.dao02.mapper.UserMapper;
import com.javaee.learning.dao02.pojo.Orders;
import com.javaee.learning.dao02.pojo.User;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.core.config.Order;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Service {
    @Test
    public void insertUser() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insertUser(new User("白冰", "123"));
        mapper.insertUser(new User("唐嫣", "123"));
        mapper.insertUser(new User("杨幂", "123"));
        mapper.insertUser(new User("刘亦菲", "123"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findAll() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findAll(null, "123");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findUserByIds() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer[] ids = {1, 2, 3};
        List<User> users = mapper.findUserByIds(ids);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void insertUserWithOrder() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
        User user = new User("白冰", "888888");
        mapper.insertUser(user);
        ordersMapper.insertOrder(new Orders("神话", 8888, user));
        ordersMapper.insertOrder(new Orders("白色连衣裙", 1333, user));
        ordersMapper.insertOrder(new Orders("头饰", 6666, user));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findAllOrders() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> orders = mapper.findAll();
        for (Orders order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void getUserAndOrders() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUserAndOrders();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
