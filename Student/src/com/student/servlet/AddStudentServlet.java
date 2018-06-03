package com.student.servlet;

import com.alibaba.fastjson.JSON;
import com.student.domin.BaseData;
import com.student.domin.Student;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;
import com.student.utils.TextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudentServlet extends HttpServlet{

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setContentType("text/html;charset=utf-8");
        try {
            String sname = request.getParameter("sname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String birthday = request.getParameter("birthday");
            String hobby = request.getParameter("hobby");
            String info = request.getParameter("info");
            Date date = null;
            if(!TextUtils.isEmpty(birthday)){
             date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            }
            Student student = new Student(sname,gender,phone,date,hobby,info);
            StudentService service = new StudentServiceImpl();
            service.addStudent(student);
            BaseData<String> baseData = new BaseData<>(1,"","新增学生成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (Exception e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0,"",e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
