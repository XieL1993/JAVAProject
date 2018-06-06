package com.student.service;

import com.student.domin.Token;
import com.student.domin.User;

import java.sql.SQLException;

public interface UserService {
    User login(User user) throws SQLException;

    void addToken(Token token) throws SQLException;

    Token findToken(String token) throws SQLException;
}
