package com.jd.mobile.service.impl;

import com.jd.mobile.dao.ProductDao;
import com.jd.mobile.dao.impl.ProductDaoImpl;
import com.jd.mobile.domin.Product;
import com.jd.mobile.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService{

    private ProductDao dao = new ProductDaoImpl();

    @Override
    public List<Product> findHots() throws Exception {
        return dao.findHots();
    }

    @Override
    public List<Product> findNews() throws Exception {
        return dao.findNews();
    }
}
