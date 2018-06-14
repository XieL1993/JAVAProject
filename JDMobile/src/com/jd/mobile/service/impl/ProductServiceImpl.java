package com.jd.mobile.service.impl;

import com.jd.mobile.dao.ProductDao;
import com.jd.mobile.dao.impl.ProductDaoImpl;
import com.jd.mobile.domin.PageBean;
import com.jd.mobile.domin.Product;
import com.jd.mobile.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao dao = new ProductDaoImpl();

    @Override
    public List<Product> findHots() throws Exception {
        return dao.findHots();
    }

    @Override
    public List<Product> findNews() throws Exception {
        return dao.findNews();
    }

    @Override
    public Product getProductDetail(String pid) throws Exception {
        return dao.getProductDetail(pid);
    }

    @Override
    public PageBean<Product> getProduct(String cid, int pageSize, int currentPage) throws SQLException {
        PageBean<Product> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int count = dao.findCount(cid);
        pageBean.setTotalSize(count);
        pageBean.setTotalPage(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        List<Product> products = dao.getProduct(cid, pageSize, currentPage);
        pageBean.setList(products);
        return pageBean;
    }
}
