package com.javaee.learning.dao;

import java.util.List;

public class Person {
    private String name;
    private List<Role> roles;

    public void setName(String name) {
        this.name = name;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
