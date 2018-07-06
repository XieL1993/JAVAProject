package com.javaee.learning.CglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy {
    private CustomerDao customerDao;

    public CglibProxy(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerDao createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(customerDao.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if ("save".equals(method.getName())) {
                    System.out.println("权限校验---cglib");
                }
                return methodProxy.invokeSuper(o, objects);
//                return method.invoke(customerDao,objects);
            }
        });
        CustomerDao customerDao = (CustomerDao) enhancer.create();
        return customerDao;
    }
}
