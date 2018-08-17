package com.javaee.learning;

import javax.servlet.*;
import java.io.IOException;

public class Filter01 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("-----------过滤器1初始化-----------");
        String filterName = filterConfig.getFilterName();
        System.out.println(filterName);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("-----------过滤器1doFilter-----------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.err.println("-----------过滤器1destroy-----------");
    }
}
