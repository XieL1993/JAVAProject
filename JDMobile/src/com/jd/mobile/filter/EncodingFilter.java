package com.jd.mobile.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private String charset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 解决post提交乱码问题
        request.setCharacterEncoding(charset);
        response.setCharacterEncoding(charset);
        response.setContentType("text/html;charset=" + charset);
        // 解决跨越
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
        // 获取HttpServletRequest对象的代理对象
        ServletRequest requestProxy = getHttpServletRequestProxy(request);
        // 这样用户在使用request对象时实际上使用的是HttpServletRequest对象的代理对象requestProxy
        filterChain.doFilter(requestProxy, response);
    }

    @Override
    public void destroy() {

    }

    private ServletRequest getHttpServletRequestProxy(final HttpServletRequest request) {
        return (ServletRequest) Proxy.newProxyInstance(this.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("get".equalsIgnoreCase(request.getMethod()) && "getParameter".equals(method.getName())) {
                    String value = (String) method.invoke(request, args);
                    if (value == null) {
                        return null;
                    }
                    return new String(value.getBytes("ISO-8859-1"), charset);
                } else {
                    return method.invoke(request, args);
                }
            }
        });
    }
}
