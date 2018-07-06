package com.javaee.learning.Test1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductService {
    @Resource(name = "productDao")
    private ProductDao dao;

    @Test
    public void a() {
        dao.save("郑爽");
        dao.update();
        dao.find();
        dao.delete();
    }
}
