package com.student.servlet;

import com.alibaba.fastjson.JSON;
import com.student.domin.BaseData;
import com.student.domin.PageBean;
import com.student.domin.Student;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;
import com.student.utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setContentType("text/html;charset=utf-8");
        try {
            String sname = request.getParameter("searchText");
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            if (!TextUtils.isEmpty(sname)) {
                sname = new String(sname.getBytes("ISO-8859-1"), "UTF-8");
            }
            StudentService service = new StudentServiceImpl();
            PageBean<Student> pageBean = service.findAll(sname, pageSize, currentPage);
            BaseData<PageBean<Student>> baseData = new BaseData<>(1, pageBean, "");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
