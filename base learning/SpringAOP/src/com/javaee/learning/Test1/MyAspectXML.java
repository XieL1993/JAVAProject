package com.javaee.learning.Test1;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspectXML {
    public void check(String name) {
        System.out.println("权限校验" + "=============" + name);
    }

    public void writeLog(Object result) {
        System.out.println("日志记录" + "=========" + result);
    }

    public Object arround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前通知===============");
        Object proceed = point.proceed();
        System.out.println("环绕后通知===============");
        return proceed;
    }

    public void error(Throwable e) {
        System.out.println("error===============" + e);
    }

    public void finalMethod() {
        System.out.println("最终通知=====================");
    }
}
