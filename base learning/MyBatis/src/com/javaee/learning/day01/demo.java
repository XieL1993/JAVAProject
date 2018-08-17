package com.javaee.learning.day01;

import com.javaee.learning.day01.mapper.UserMapper;
import com.javaee.learning.day01.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class demo {

    @Test
    public void insertUser() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();

        User user = new User();
        user.setUsername("杨幂");
        user.setAge(20);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.insertUser(user);
        System.out.println(i);
        System.out.println(user.getUid());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void findUserById() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(7);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void findAll() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void updateName() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUid(7);
        user.setUsername("唐嫣");
        mapper.updateName(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser(4);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUserByName() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUserByName("爽");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void test() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream stream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);
        SqlSession sqlSession = factory.openSession();
        User user = new User();
        user.setUsername("鞠婧祎");
        user.setAge(16);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.insertUser(user);
        User userById = mapper.findUserById(user.getUid());
        System.out.println(userById);
        sqlSession.commit();
        sqlSession.close();

    }
}
