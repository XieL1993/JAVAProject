package com.javaee.learning.dao02;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Integer id;
    private String name;
    private Set<Linkman> linkMans = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Linkman> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<Linkman> linkMans) {
        this.linkMans = linkMans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
