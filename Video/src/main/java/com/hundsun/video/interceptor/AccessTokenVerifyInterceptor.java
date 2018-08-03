package com.hundsun.video.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hundsun.video.entity.Result;
import com.hundsun.video.entity.VUser;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.exception.VException;
import com.hundsun.video.service.IVUserService;
import com.hundsun.video.utils.JwtToken;
import com.hundsun.video.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessTokenVerifyInterceptor implements HandlerInterceptor {
    @Autowired
    private IVUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String token = request.getHeader("token");
            if (StringUtils.isNotBlank(token)) {
                Integer uid = JwtToken.getAppUID(token);
                VUser user = userService.selectById(uid);
                if (user != null) {
                    System.out.println("================用户访问================");
                    System.out.println(user);
                    System.out.println("================用户访问================");
                    return true;
                } else {
                    sendResponse(response, ResultUtils.error(ResultEnums.ERROR_USER));
                    return false;
                }
            } else {
                sendResponse(response, ResultUtils.error(ResultEnums.UN_LOGOIN));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof VException) {
                sendResponse(response, ResultUtils.error((VException) e));
            } else {
                sendResponse(response, ResultUtils.error(ResultEnums.UNKONW_ERROR.getCode(), e.getMessage()));
            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

    private void sendResponse(HttpServletResponse response, Result result) throws IOException {
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
