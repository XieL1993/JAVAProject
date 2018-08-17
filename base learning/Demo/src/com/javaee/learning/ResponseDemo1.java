package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String flag = req.getParameter("flag");
        if (flag.equals("a")) {
            resp.getWriter().write("将乘秋风扫华容，偏拾此间问卷");
        } else {
            resp.getOutputStream().write("鞠婧祎666".getBytes("UTF-8"));
        }
    }
}
