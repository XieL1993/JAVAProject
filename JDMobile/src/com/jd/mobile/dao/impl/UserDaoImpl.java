package com.jd.mobile.dao.impl;

import com.jd.mobile.dao.UserDao;
import com.jd.mobile.domin.Token;
import com.jd.mobile.domin.User;
import com.jd.mobile.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    @Override
    public void register(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        runner.update("insert into user values ( ? , ? , ? ,? )",user.getUid(),user.getUsername(),user.getPassword(),user.getTime());
    }

    @Override
    public User login(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from user where username = ? and password = ? " , new BeanHandler<>(User.class),user.getUsername(),user.getPassword());
    }

    @Override
    public void addToken(Token token) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        runner.update("insert into token values (? , ? ,? )", token.getTid(), token.getUsername(), token.getTime());
    }

    @Override
    public Token findToken(String token) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from token where tid = ? " , new BeanHandler<>(Token.class),token);
    }
}
