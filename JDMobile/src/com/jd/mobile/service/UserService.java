package com.jd.mobile.service;

import com.jd.mobile.domin.Token;
import com.jd.mobile.domin.User;

import java.sql.SQLException;

public interface UserService {

    void register(User user) throws SQLException;

    User login(User user) throws SQLException;

    User find(String uid) throws SQLException;

    void addToken(Token token) throws SQLException;

    Token findToken(String token) throws SQLException;
}
