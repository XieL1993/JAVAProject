package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServletContextDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        method1();
        method2();
        method3();
    }

    private void method3() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().
                getResourceAsStream("../../file/config.properties"));
        String name = properties.getProperty("name");
        System.out.println("method3:" + name);
    }

    private void method2() throws IOException {
        Properties properties = new Properties();
        properties.load(getServletContext().getResourceAsStream("file/config.properties"));
        String name = properties.getProperty("name");
        System.out.println("method2:" + name);

    }

    private void method1() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(getServletContext().getRealPath("file/config.properties")));
        String name = properties.getProperty("name");
        System.out.println("method1:" + name);
    }
}
