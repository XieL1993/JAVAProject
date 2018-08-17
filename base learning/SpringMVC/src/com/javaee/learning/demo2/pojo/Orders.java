package com.javaee.learning.demo2.pojo;

public class Orders {
    private int oid;
    private String product;
    private String price;
    private int uid;

    public Orders() {
    }

    public Orders(int oid, String product, String price, int uid) {
        this.oid = oid;
        this.product = product;
        this.price = price;
        this.uid = uid;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", product='" + product + '\'' +
                ", price='" + price + '\'' +
                ", uid=" + uid +
                '}';
    }
}
