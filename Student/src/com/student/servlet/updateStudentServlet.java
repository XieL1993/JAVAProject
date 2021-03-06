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

public class updateStudentServlet extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int sid = Integer.parseInt(request.getParameter("sid"));
            String sname = request.getParameter("sname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String birthday = request.getParameter("birthday");
            String hobby = request.getParameter("hobby");
            String info = request.getParameter("info");
            Date date = null;
            if (!TextUtils.isEmpty(birthday)) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            }
            Student student = new Student(sid, sname, gender, phone, date, hobby, info);
            StudentService service = new StudentServiceImpl();
            service.updateStudent(student);
            System.out.println("--------编辑学生成功--------");
            BaseData<String> baseData = new BaseData<>(1, "", "编辑学生成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (Exception e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
