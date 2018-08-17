package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Jiesuan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Map<String, Integer> map = (Map<String, Integer>) req.getSession().getAttribute("cart");
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                sb.append("<br/>");
                String text = "<h2>" + key + ":" + value + "</h2>";
                sb.append(text);
            }
        }
        resp.getWriter().write(sb.toString());
    }
}
