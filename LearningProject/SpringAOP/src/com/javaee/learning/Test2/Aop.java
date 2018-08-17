package com.javaee.learning.Test2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aop {
    @Pointcut(value = "execution(* com.javaee.learning.Test2.Actor.save(..))")
    private void point1() {
    }

    @Before(value = "Aop.point1() and args(name)")
    public void beforeSave(String name) {
        System.out.println("前置增强=============" + name);
    }

    @AfterReturning(value = "execution(* com.javaee.learning.Test2.Actor.find(..))", returning = "result")
    public void afterFind(String result) {
        System.out.println("后置增强=============" + result);
    }

    @Around(value = "execution(* com.javaee.learning.Test2.Actor.update(..))")
    public Object arroundUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前置环绕=============");
        String proceed = (String) joinPoint.proceed();
        System.out.println("后置环绕=============");
        return proceed.equals("1") ? "更新成功" : "更新失败";
    }

    @AfterThrowing(value = "execution(* com.javaee.learning.Test2.Actor.*(..))", throwing = "e")
    public void errorCatch(Throwable e) {
        System.out.println("异常增强=============" + e);
    }

    @After(value = "execution(* com.javaee.learning.Test2.Actor.*(..))")
    public void finalMethod() {
        System.err.println("=========================");
    }
}
