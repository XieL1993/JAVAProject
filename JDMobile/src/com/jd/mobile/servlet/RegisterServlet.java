package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.User;
import com.jd.mobile.service.UserService;
import com.jd.mobile.service.impl.UserServiceImpl;
import com.jd.mobile.utils.BeanFactory;
import com.jd.mobile.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = BeanFactory.populate(User.class,request.getParameterMap());
            user.setUid(UUIDUtils.getUUID());
            user.setTime(new Date());
            UserService service = new UserServiceImpl();
            service.register(user);
            System.out.println("--------注册成功--------");
            BaseData<String> baseData = new BaseData<>(1,"","注册成功");
            response.getWriter().write(JSON.toJSONString(baseData));
        } catch (SQLException e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0,"",e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }

}
