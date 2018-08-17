package com.javaee.learning;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ServletContextDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        Enumeration<String> names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String element = names.nextElement();
            String value = context.getInitParameter(element);
            System.out.println(element + "------" + value);
        }
        String realPath = context.getRealPath("file/config.properties");
        System.out.println(realPath);
        InputStream stream = new FileInputStream(realPath);
        Properties properties = new Properties();
        properties.load(stream);
        String name = properties.getProperty("name");
        System.out.println(name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
