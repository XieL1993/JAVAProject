package com.jd.mobile.service;

import com.jd.mobile.domin.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHots()throws Exception;

    List<Product> findNews()throws Exception;
}
