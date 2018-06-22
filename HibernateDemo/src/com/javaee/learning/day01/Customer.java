package com.javaee.learning.day01;

public class Customer {
    int cid;
    String cusName;
    int age;
    String hobby;
    String description;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cusName='" + cusName + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
