package com.javaee.learning;

import java.lang.reflect.Method;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("com.javaee.learning.Test");
        Object object = c.newInstance();
        Method[] methods = c.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(MyTest.class)) {
                MyTest myTest = m.getDeclaredAnnotation(MyTest.class);
                long timeout = myTest.timeout();
                for (int i = 0; i < timeout; i++) {
                    System.out.println("------" + i + "------");
                }
                m.invoke(object);
            }
        }
    }
}

