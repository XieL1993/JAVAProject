package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookieDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            System.out.println("--------------------------------");
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "=" + URLDecoder.decode(cookie.getValue(), "utf-8"));
            }
        }
        Cookie cookie = new Cookie("favor", URLEncoder.encode("鞠婧祎", "utf-8"));
        cookie.setMaxAge(10);
        resp.addCookie(cookie);
        resp.getWriter().write("请求成功了");
    }
}
