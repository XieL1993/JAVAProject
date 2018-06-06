package com.student.dao.impl;

import com.student.dao.UserDao;
import com.student.domin.Token;
import com.student.domin.User;
import com.student.utils.SourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User login(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        return runner.query("select * from t_user where username = ? and password = ? ", new BeanHandler<>(User.class), user.getUsername(), user.getPassword());
    }

    @Override
    public void addToken(Token token) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        runner.update("insert into t_token values ( ? , ? )", token.getToken(), token.getExpiry());
    }

    @Override
    public Token findToken(String token) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        return runner.query("select * from t_token where token = ? ", new BeanHandler<>(Token.class), token);
    }
}
