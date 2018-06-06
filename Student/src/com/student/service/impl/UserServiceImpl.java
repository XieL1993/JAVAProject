package com.student.service.impl;

import com.student.dao.UserDao;
import com.student.dao.impl.UserDaoImpl;
import com.student.domin.Token;
import com.student.domin.User;
import com.student.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
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
