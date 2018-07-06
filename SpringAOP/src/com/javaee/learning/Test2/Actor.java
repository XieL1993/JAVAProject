package com.javaee.learning.Test2;

import org.springframework.stereotype.Repository;

@Repository("actor")
public class Actor {
    public void save(String name) {
        System.out.println("新增");
    }

    public String find(int id) {
        System.out.println("查询");
        return "郑爽";
    }

    public String update() {
        System.out.println("更新");
        return "1";
    }

    public void delete() {
        String a = null;
        a.split("-");
    }
}
