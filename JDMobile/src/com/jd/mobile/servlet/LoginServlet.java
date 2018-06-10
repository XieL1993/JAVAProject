package com.jd.mobile.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.Token;
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

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User bean = BeanFactory.populate(User.class,request.getParameterMap());
            UserService service = new UserServiceImpl();
            User user = service.login(bean);
            if(user!=null){
                System.out.println("--------登录成功--------");
                // 插入token
                Token token = new Token(UUIDUtils.getUUID(),user.getUsername(),new Date());
                service.addToken(token);
                JSONObject object = new JSONObject();
                object.put("token", token.getTid());
                object.put("username", user.getUsername());
                object.put("password", user.getPassword());
                // 返回登录信息
                BaseData<JSONObject> baseData = new BaseData<>(1, object, "登录成功");
                response.getWriter().write(JSON.toJSONString(baseData));
            }else {
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
