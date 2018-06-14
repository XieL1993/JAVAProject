package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.PageBean;
import com.jd.mobile.domin.Product;
import com.jd.mobile.service.ProductService;
import com.jd.mobile.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "productList", urlPatterns = "/product")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cid = req.getParameter("cid");
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
            ProductService service = new ProductServiceImpl();
            PageBean<Product> pageBean = service.getProduct(cid, pageSize, currentPage);
            BaseData<PageBean<Product>> baseData = new BaseData<>(1, pageBean, "获取商品列表成功");
            resp.getWriter().write(JSON.toJSONString(baseData));
        } catch (SQLException e) {
            e.printStackTrace();
            BaseData<PageBean<Product>> baseData = new BaseData<>(0, null, e.getMessage());
            resp.getWriter().write(JSON.toJSONString(baseData));
        }

    }
}
