package com.jd.mobile.dao;

import com.jd.mobile.domin.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findHots()throws Exception;

    List<Product> findNews()throws Exception;

}
