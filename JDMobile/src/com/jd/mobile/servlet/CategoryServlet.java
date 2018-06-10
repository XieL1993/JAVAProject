package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.Category;
import com.jd.mobile.service.CategoryService;
import com.jd.mobile.service.impl.CategoryServiceImpl;
import com.jd.mobile.utils.JedisUtils;
import com.jd.mobile.utils.TextUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "category",urlPatterns = "/category")
public class CategoryServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Jedis jedis = JedisUtils.getJedis();
            String jsonStr = jedis.get("category");
            if(TextUtils.isEmpty(jsonStr)){
                CategoryService service = new CategoryServiceImpl();
                List<Category> list = service.getAllCategory();
                jsonStr = JSONArray.toJSONString(list);
                jedis.set("category",jsonStr);
            }
            JedisUtils.closeJedis(jedis);
            JSONArray jsonArray = JSONObject.parseArray(jsonStr);
            BaseData<JSONArray> baseData = new BaseData<>(1, jsonArray, "获取分类成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (Exception e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
