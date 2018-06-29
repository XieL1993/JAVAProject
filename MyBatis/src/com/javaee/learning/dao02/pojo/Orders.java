package com.javaee.learning.dao02.pojo;

public class Orders {
    private int oid;
    private String product;
    private double price;
    private User user;

    public Orders() {
    }

    public Orders(String product, double price, User user) {
        this.product = product;
        this.price = price;
        this.user = user;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", product='" + product + '\'' +
                ", price=" + price +
                '}';
    }
    /*@Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", product='" + product + '\'' +
                ", price=" + price +
                ", user=[" + user.getUid() + user.getUserName() + "]" +
                '}';
    }*/
}
