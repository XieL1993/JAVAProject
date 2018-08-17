package com.javaee.learning;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "------" + password);
        PrintWriter writer = resp.getWriter();
        if ("admin".equals(username) && "123".equals(password)) {
            ServletContext context = getServletContext();
            Object count = context.getAttribute("count");
            int totalCount = 0;
            if (count != null) {
                totalCount = (int) count;
            }
            totalCount++;
            context.setAttribute("count", totalCount);
            resp.setStatus(302);
            resp.setHeader("location", "login_success.html");
        } else {
            writer.print("login failed");
        }
    }
}
