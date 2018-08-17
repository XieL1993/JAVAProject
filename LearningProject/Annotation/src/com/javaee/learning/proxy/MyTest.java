package com.javaee.learning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyTest {
    public static void main(String[] args) {
        Icar icar = (Icar) Proxy.newProxyInstance(MyTest.class.getClassLoader(), MyCar.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equalsIgnoreCase("start")) {
                    System.out.println("汽车即将发动，请系好安全带");
                    return method.invoke(new MyCar(), args);
                } else {
                    return method.invoke(new MyCar(), args);
                }
            }
        });
        String result = icar.start("100", 2);
        System.out.println(result);
        icar.run();
        icar.stop();
    }
}
