package com.hundsun.house.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConf extends WebMvcConfigurationSupport {
    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private AuthActionInterceptor authActionInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).excludePathPatterns("/static")
                .addPathPatterns("/**");

        registry.addInterceptor(authActionInterceptor).addPathPatterns("/house/toAdd")
                .addPathPatterns("/accounts/profile").addPathPatterns("/accounts/profileSubmit")
                .addPathPatterns("/house/bookmarked").addPathPatterns("/house/del")
                .addPathPatterns("/house/ownlist").addPathPatterns("/house/add")
                .addPathPatterns("/house/toAdd").addPathPatterns("/agency/agentMsg")
                .addPathPatterns("/comment/leaveComment").addPathPatterns("/comment/leaveBlogComment");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/static/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:D:/house/");
        super.addResourceHandlers(registry);
    }
}
