package com.javaee.learning.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Course {
    private String[] array;
    private List<String> list;
    private Map<String,String> map;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Course{" +
                "array=" + Arrays.toString(array) +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
