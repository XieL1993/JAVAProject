package com.javaee.learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RequestDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Enumeration<String> names = req.getHeaderNames();
//        while (names.hasMoreElements()) {
//            String key = names.nextElement();
//            String value = req.getHeader(key);
//            System.out.println(key + "=" + value);
//        }
        Map<String, String[]> map = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            StringBuilder s = new StringBuilder();
            for (String v : value) {
                v = new String(v.getBytes("ISO-8859-1"), "UTF-8");
                if (!s.toString().equals("")) {
                    s.append(",");
                }
                s.append(v);
            }
            System.out.println(key + "===" + s.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8"); post 的时处理中文编码
        doGet(req, resp);
    }
}
