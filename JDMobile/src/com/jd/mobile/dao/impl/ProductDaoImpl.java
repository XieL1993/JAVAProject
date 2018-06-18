package com.jd.mobile.dao.impl;

import com.jd.mobile.dao.ProductDao;
import com.jd.mobile.domin.Product;
import com.jd.mobile.utils.JDBCUtils;
import com.jd.mobile.utils.TextUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findHots() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from product where pflag = 0 and is_hot = 1 order by pdate desc limit 0,10", new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> findNews() throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from product where pflag = 0 order by pdate desc limit 0,10", new BeanListHandler<>(Product.class));
    }

    @Override
    public Product getProductDetail(String pid) throws Exception {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        return runner.query("select * from product where pid = ?", new BeanHandler<>(Product.class), pid);
    }

    @Override
    public int findCount(String cid) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select count(*) from product where 1 = 1 ";
        List<Object> list = new ArrayList<>();
        if (!TextUtils.isEmpty(cid)) {
            sql += "and cid = ? ";
            list.add(cid);
        }
        Long count = (Long) runner.query(sql, new ScalarHandler(), list.toArray());
        return count.intValue();
    }

    @Override
    public List<Product> getProduct(String cid, int pageSize, int currentPage) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from product where 1 = 1 ";
        List<Object> list = new ArrayList<>();
        if (!TextUtils.isEmpty(cid)) {
            sql += "and cid = ? ";
            list.add(cid);
        }
        if (pageSize > 0 && currentPage > 0) {
            sql += "limit ? offset ? ";
            list.add(pageSize);
            list.add((currentPage - 1) * pageSize);
        }
        return runner.query(sql, new BeanListHandler<>(Product.class), list.toArray());
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
        qr.update(sql,params);
    }
}
