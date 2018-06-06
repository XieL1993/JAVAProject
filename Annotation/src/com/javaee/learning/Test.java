package com.javaee.learning;

public class Test {
    @MyTest(timeout = 30)
    public void add() {
        System.out.println("---------ADD---------");
    }

    @MyTest(timeout = 10)
    public void delete() {
        System.out.println("---------DELETE---------");
    }

    @MyTest(timeout = 20)
    public void update() {
        System.out.println("---------UPDATE---------");
    }

    public void query() {
        System.out.println("---------QUERY---------");
    }

    public void search() {
        System.out.println("---------SEARCH---------");
    }

    public void findAll() {
        System.out.println("---------FINDALL---------");
    }
}
