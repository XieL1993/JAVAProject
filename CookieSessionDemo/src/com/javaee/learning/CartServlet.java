package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartServlet extends HttpServlet {
    private String[] names = {"郑爽", "杨幂", "陈乔恩", "鞠婧祎", "范冰冰"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = names[id];
        Map<String, Integer> map = (Map<String, Integer>) request.getSession().
                getAttribute("cart");
        if (map == null) {
            map = new LinkedHashMap<>();
            request.getSession().setAttribute("cart", map);
        }
        map.put(name, map.containsKey(name) ? map.get(name) + 1 : 1);
        response.getWriter().write("<a href='product_list.html'><h3>继续购物</h3></a>");
        response.getWriter().write("<a href='cart.html'><h3>去购物车结算</h3></a>");

    }
}
