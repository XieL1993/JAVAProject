package com.student.servlet;

import com.alibaba.fastjson.JSON;
import com.student.domin.BaseData;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setContentType("text/html;charset=utf-8");
        try {
            int sid = Integer.parseInt(request.getParameter("sid"));
            StudentService service = new StudentServiceImpl();
            service.deleteStudent(sid);
            System.out.println("--------删除学生成功--------");
            BaseData<String> baseData = new BaseData<>(1, "", "删除学生成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (SQLException e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
