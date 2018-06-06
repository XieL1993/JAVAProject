package com.student.dao;

import com.student.domin.Token;
import com.student.domin.User;

import java.sql.SQLException;

public interface UserDao {
    User login(User user) throws SQLException;

    void addToken(Token token) throws SQLException;

    Token findToken(String token) throws SQLException;
}
