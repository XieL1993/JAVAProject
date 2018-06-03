package com.student.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.student.domin.BaseData;
import com.student.domin.Student;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html;charset=utf-8");
        try {
            StudentService service = new StudentServiceImpl();
            List<Student> studentList = service.findAll();
            BaseData<List<Student>> baseData = new BaseData<>(1,studentList,"");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
