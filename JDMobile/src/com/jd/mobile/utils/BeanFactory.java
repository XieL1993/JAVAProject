package com.jd.mobile.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Map;

public class BeanFactory {
    public static <T> T populate(Class<T> clazz,Map<String,String[]> map){
        try {
            T obj = clazz.newInstance();
            DateConverter converter = new DateConverter();
            converter.setPattern("yyyy-MM-dd");
            ConvertUtils.register(converter,java.util.Date.class);
            BeanUtils.populate(obj,map);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
