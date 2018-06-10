package com.jd.mobile.dao;

import com.jd.mobile.domin.Token;
import com.jd.mobile.domin.User;

import java.sql.SQLException;

public interface UserDao {

    void register(User user) throws SQLException;

    User login(User user) throws SQLException;

    void addToken(Token token) throws SQLException;

    Token findToken(String token) throws SQLException;
}
