package com.jd.mobile.dao;

import com.jd.mobile.domin.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategory() throws Exception;
}
