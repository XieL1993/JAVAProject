package com.javaee.learning.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    private UserDao userDao;

    public JDKProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao createProxy() {
        UserDao userDaoProxy = (UserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("权限校验");
                }
                return method.invoke(userDao, args);
            }
        });
        return userDaoProxy;
    }
}
