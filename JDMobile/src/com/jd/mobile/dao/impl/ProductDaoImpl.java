package com.jd.mobile.dao.impl;

import com.jd.mobile.dao.ProductDao;
import com.jd.mobile.domin.Product;
import com.jd.mobile.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public List<Product> findHots() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from product where pflag = 0 and is_hot = 1 order by pdate desc limit 0,10",new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> findNews() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from product where pflag = 0 order by pdate desc limit 0,10",new BeanListHandler<>(Product.class));
    }
}
