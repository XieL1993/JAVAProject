package com.jd.mobile.dao;

import com.jd.mobile.domin.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Product> findHots() throws Exception;

    List<Product> findNews() throws Exception;

    Product getProductDetail(String pid) throws Exception;

    int findCount(String cid) throws SQLException;

    List<Product> getProduct(String cid, int pageSize, int currentPage) throws SQLException;

}
