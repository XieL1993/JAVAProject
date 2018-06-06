package com.student.servlet;

import com.alibaba.fastjson.JSON;
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

public class StudentDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int sid = Integer.parseInt(request.getParameter("sid"));
            StudentService service = new StudentServiceImpl();
            Student student = service.findStudentById(sid);
            System.out.println("--------获取学生详情成功--------");
            BaseData<Student> baseData = new BaseData<>(1, student, "获取学生详情成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (SQLException e) {
            e.printStackTrace();
            BaseData<Student> baseData = new BaseData<>(0, null, e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
