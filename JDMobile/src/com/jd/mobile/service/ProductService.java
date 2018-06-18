package com.jd.mobile.service;

import com.jd.mobile.domin.PageBean;
import com.jd.mobile.domin.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findHots() throws Exception;

    List<Product> findNews() throws Exception;

    Product getProductDetail(String pid) throws Exception;

    PageBean<Product> getProduct(String cid, int pageSize, int currentPage) throws SQLException;

    void addProduct(Product product) throws SQLException;
}
