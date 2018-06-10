package com.jd.mobile.dao.impl;

import com.jd.mobile.dao.CategoryDao;
import com.jd.mobile.domin.Category;
import com.jd.mobile.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao{
    @Override
    public List<Category> getAllCategory() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from category",new BeanListHandler<>(Category.class));
    }
}
