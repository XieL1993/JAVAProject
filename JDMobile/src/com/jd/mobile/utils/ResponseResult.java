package com.jd.mobile.utils;

import com.alibaba.fastjson.JSON;
import com.jd.mobile.domin.BaseData;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseResult {
    public static void error(HttpServletResponse response, Exception e) throws IOException {
        BaseData<String> baseData = new BaseData<>(0, "", e.getMessage());
        response.getWriter().write(JSON.toJSONString(baseData));
    }

    public static <T> void success(HttpServletResponse response, T t, String msg) throws IOException {
        BaseData<T> baseData = new BaseData<>(1, t, msg);
        response.getWriter().write(JSON.toJSONString(baseData));
    }
}
