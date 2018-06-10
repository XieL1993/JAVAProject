package com.jd.mobile.service.impl;

import com.jd.mobile.dao.UserDao;
import com.jd.mobile.dao.impl.UserDaoImpl;
import com.jd.mobile.domin.Token;
import com.jd.mobile.domin.User;
import com.jd.mobile.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    @Override
    public void register(User user) throws SQLException {
        UserDao dao = new UserDaoImpl();
        dao.register(user);
    }

    @Override
    public User login(User user) throws SQLException {
        UserDao dao = new UserDaoImpl();
        return dao.login(user);
    }

    @Override
    public void addToken(Token token) throws SQLException {
        UserDao dao = new UserDaoImpl();
        dao.addToken(token);
    }

    @Override
    public Token findToken(String token) throws SQLException {
        UserDao dao = new UserDaoImpl();
        return dao.findToken(token);
    }
}
