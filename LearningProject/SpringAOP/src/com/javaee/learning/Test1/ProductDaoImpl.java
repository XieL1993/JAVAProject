package com.javaee.learning.Test1;

import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
    @Override
    public void save(String name) {
        System.out.println("新增商品");
    }

    @Override
    public void update() {
        System.out.println("修改商品");
    }

    @Override
    public void find() {
        System.out.println("查询商品");
        int a = 1 / 0;
    }

    @Override
    public String delete() {
        System.out.println("删除商品");
        return "删除成功";
    }
}
