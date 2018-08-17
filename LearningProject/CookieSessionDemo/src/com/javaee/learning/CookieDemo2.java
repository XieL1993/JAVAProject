package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CookieDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        if ("郑爽".equals(username) && "123".equals(password)) {
            Cookie[] cookies = req.getCookies();
            Cookie last = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("last".equals(cookie.getName())) {
                        last = cookie;
                    }
                }
            }
            if (last == null) {
                last = new Cookie("last", System.currentTimeMillis() + "");
                last.setMaxAge(60 * 60);
                resp.addCookie(last);
                resp.getWriter().write("欢迎您：" + username);
            } else {
                long value = Long.parseLong(last.getValue());
                resp.getWriter().write("欢迎您：" + username + "，上次登录时间：" + new Date(value));
                last.setValue(System.currentTimeMillis() + "");
                resp.addCookie(last);
            }
        } else {
            resp.getWriter().write("用户名或密码错误！");
        }
    }
}
