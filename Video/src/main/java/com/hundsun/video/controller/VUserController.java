package com.hundsun.video.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.video.entity.Result;
import com.hundsun.video.entity.UserInfo;
import com.hundsun.video.entity.VUser;
import com.hundsun.video.entity.WxLoginInfo;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.service.IVUserService;
import com.hundsun.video.utils.IpUtil;
import com.hundsun.video.utils.JwtToken;
import com.hundsun.video.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xl
 * @since 2018-07-30
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = {"用户相关业务的接口"})
public class VUserController {
    @Autowired
    private IVUserService userService;
    @Autowired
    private WxMaService wxService;

    @PostMapping(value = "login_by_weixin")
    public Result loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        if (code == null || userInfo == null) {
            return ResultUtils.error(ResultEnums.BAD_PARAMS);
        }
        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sessionKey == null || openId == null) {
            return ResultUtils.error(ResultEnums.LOGIN_FAILED);
        }
        VUser user = userService.queryByOpenId(openId);
        if (user == null) {
            user = new VUser();
            user.setUsername(userInfo.getNickName());
            user.setPassword(openId);
            user.setGender(userInfo.getGender());
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            user.setNickname(userInfo.getNickName());
            user.setAvatar(userInfo.getAvatarUrl());
            user.setCountry(userInfo.getCountry());
            user.setProvince(userInfo.getProvince());
            user.setCity(userInfo.getCity());
            user.setWeixinOpenid(openId);
            user.setAddTime(new Date());
            userService.insert(user);
        } else {
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            userService.updateById(user);
        }
        String token = null;
        try {
            token = JwtToken.createToken(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(token)) {
            return ResultUtils.error(ResultEnums.LOGIN_FAILED);
        }
        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("userInfo", user);
        return ResultUtils.success(result);
    }

    @GetMapping("test")
    public Result test() {
        return ResultUtils.success("测试token");
    }

}
