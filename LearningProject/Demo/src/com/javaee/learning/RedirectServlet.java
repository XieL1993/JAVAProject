package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
        String password = req.getParameter("password");
        if ("郑爽".equals(username) && "123".equals(password)) {
            resp.sendRedirect("login_success2.html");
        } else {
            req.getRequestDispatcher("login_failed.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
