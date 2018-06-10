package com.jd.mobile.service.impl;

import com.jd.mobile.dao.CategoryDao;
import com.jd.mobile.dao.impl.CategoryDaoImpl;
import com.jd.mobile.domin.Category;
import com.jd.mobile.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{
    @Override
    public List<Category> getAllCategory() throws Exception {
        CategoryDao dao = new CategoryDaoImpl();
        return dao.getAllCategory();
    }
}
