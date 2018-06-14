package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "image", urlPatterns = "/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String image = request.getParameter("image");
        InputStream in = getServletContext().getResourceAsStream("image/" + image);
        if (in == null) {
            BaseData<String> baseData = new BaseData<>(0, "", "image load failed");
            response.getWriter().write(JSON.toJSONString(baseData));
        } else {
            ServletOutputStream out = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
        }
    }
}
