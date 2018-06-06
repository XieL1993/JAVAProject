package com.student.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.student.domin.BaseData;
import com.student.domin.Token;
import com.student.domin.User;
import com.student.service.UserService;
import com.student.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
            String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");
            // 查询用户表
            User user = new User(username, password);
            UserService service = new UserServiceImpl();
            User bean = service.login(user);
            if (bean != null) {
                // 插入token
                String sessionId = request.getSession(true).getId();
                Token token = new Token(sessionId, new Date());
                service.addToken(token);
                JSONObject object = new JSONObject();
                object.put("token", sessionId);
                object.put("username", username);
                object.put("password", password);
                // 返回登录信息
                BaseData<JSONObject> baseData = new BaseData<>(1, object, "登录成功");
                response.getWriter().write(JSON.toJSONString(baseData));
            } else {
                BaseData<String> baseData = new BaseData<>(0, "", "用户名或密码错误，请重新登录");
                response.getWriter().write(JSON.toJSONString(baseData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
            response.getWriter().write(JSON.toJSONString(baseData));
        }
    }
}
