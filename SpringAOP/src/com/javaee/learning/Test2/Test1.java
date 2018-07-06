package com.javaee.learning.Test2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class Test1 {
    @Resource(name = "actor")
    private Actor actor;

    @Test
    public void a() {
        actor.save("郑爽");
        actor.find(1);
        System.out.println(actor.update());
//        actor.delete();
    }
}
