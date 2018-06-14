package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.Product;
import com.jd.mobile.service.ProductService;
import com.jd.mobile.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "index", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductService service = new ProductServiceImpl();
            List<Product> hots = service.findHots();
            List<Product> news = service.findNews();
            JSONObject object = new JSONObject();
            object.put("hots", hots);
            object.put("news", news);
            JSONArray array = new JSONArray();
            array.add("banner/a.jpg");
            array.add("banner/b.jpeg");
            array.add("banner/c.jpg");
            object.put("banners", array);
            BaseData<JSONObject> baseData = new BaseData<>(1, object, "获取首页成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (Exception e) {
            e.printStackTrace();
            BaseData<JSONObject> baseData = new BaseData<>(1, null, e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
