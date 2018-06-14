package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.Product;
import com.jd.mobile.service.ProductService;
import com.jd.mobile.service.impl.ProductServiceImpl;
import com.jd.mobile.utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "productDetail", urlPatterns = "/product/detail")
public class ProductDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pid = req.getParameter("pid");
            if (TextUtils.isEmpty(pid)) {
                BaseData<String> baseData = new BaseData<>(0, "", "pid is null");
                resp.getWriter().write(JSON.toJSONString(baseData));
            } else {
                ProductService service = new ProductServiceImpl();
                Product detail = service.getProductDetail(pid);
                BaseData<Product> baseData = new BaseData<>(1, detail, "获取产品详情成功");
                resp.getWriter().write(JSON.toJSONString(baseData));
            }
        } catch (Exception e) {
            e.printStackTrace();
            BaseData<Product> baseData = new BaseData<>(0, null, e.getMessage());
            resp.getWriter().write(JSON.toJSONString(baseData));
        }

    }
}
