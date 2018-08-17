package com.hundsun.house.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterBeanConfig {
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<LogFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogFilter());
        List<String> urls = new ArrayList<>();
        urls.add("*");
        bean.setUrlPatterns(urls);
        return bean;

    }
}
