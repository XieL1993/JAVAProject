package com.jd.mobile.filter;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;
import com.jd.mobile.domin.Token;
import com.jd.mobile.service.UserService;
import com.jd.mobile.service.impl.UserServiceImpl;
import com.jd.mobile.utils.TextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "tokenFilter",urlPatterns = "/*")
public class TokenFilter implements Filter{
    private long expiry = 1000 * 60 * 60;// 有效期 60分钟
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        System.out.println(token + "---" + requestURI + "---" + method);
        if ("OPTIONS".equals(method) || "/login".equals(requestURI)||"/register".equals(requestURI)) {
            filterChain.doFilter(request, response);
        } else {
            if (checkToken(token)) {
                filterChain.doFilter(request, response);
            } else {
                BaseData<String> baseData = new BaseData<>(403, "", "token失效，请重新登录");
                response.getWriter().write(JSON.toJSONString(baseData));
            }
        }
    }

    @Override
    public void destroy() {

    }
    private boolean checkToken(String token) {
        if (!TextUtils.isEmpty(token)) {
            try {
                UserService service = new UserServiceImpl();
                Token tokenBean = service.findToken(token);
                if (tokenBean != null) {
                    long time = new Date().getTime() - tokenBean.getTime().getTime();
                    if (time < expiry) {
                        // token有效
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
