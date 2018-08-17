package com.javaee.learning.demo1.dao.impl;

import com.javaee.learning.demo1.dao.UserDao;
import com.javaee.learning.demo1.mapper.UserMapper;
import com.javaee.learning.demo1.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
    @Override
    public List<User> findAll() {
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.findAll();
    }
}
