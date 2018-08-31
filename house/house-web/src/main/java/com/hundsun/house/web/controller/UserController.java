package com.hundsun.house.web.controller;

import com.google.common.collect.Lists;
import com.hundsun.house.bean.User;
import com.hundsun.house.bean.vo.UserVo;
import com.hundsun.house.constants.CommonConstants;
import com.hundsun.house.result.ResultMsg;
import com.hundsun.house.service.UserService;
import com.hundsun.house.utils.HashUtils;
import com.hundsun.house.utils.UserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/accounts/register")
    public String accountsRegister(UserVo userVo, ModelMap modelMap) {
        if (userVo == null || userVo.getName() == null) {
//            modelMap.put()
            modelMap.put("agencyList", Lists.newArrayList());
            return "/user/accounts/register";
        }
        ResultMsg resultMsg = UserHelper.validate(userVo);
        if (resultMsg.isSuccess() && userService.addAccount(userVo)) {
            modelMap.put("email", userVo.getEmail());
            return "/user/accounts/registerSubmit";
        } else {
            return "redirect:/accounts/register?" + resultMsg.asUrlParams();
        }
    }

    @RequestMapping("accounts/verify")
    public String verify(String key) {
        boolean enable = userService.enable(key);
        if (enable) {
            return "redirect:/index?" + ResultMsg.successMsg("激活成功").asUrlParams();
        } else {
            return "redirect:/accounts/register?" + ResultMsg.errorMsg("激活失败,请确认链接是否过期").asUrlParams();
        }
    }

    @RequestMapping("/accounts/signin")
    public String signin(String username, String password, String target, HttpServletRequest request) {
        if (username == null || password == null) {
            request.setAttribute("target", target);
            return "/user/accounts/signin";
        }
        User auth = userService.auth(username, password);
        if (auth == null) {
            return "redirect:/accounts/signin?" + "target=" + target + "&username=" + username + "&" + ResultMsg.errorMsg("用户名或密码错误").asUrlParams();
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(CommonConstants.USER_ATTRIBUTE, auth);
            return StringUtils.isNotBlank(target) ? "redirect:" + target : "redirect:/index";
        }
    }

    @RequestMapping("accounts/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/index";
    }

    @RequestMapping("accounts/profile")
    public String profile(HttpServletRequest request, UserVo userVo, ModelMap modelMap) {
        if (userVo.getEmail() == null) {
            return "/user/accounts/profile";
        }
        userService.updateUser(userVo, userVo.getEmail());
        User user = new User();
        user.setEmail(userVo.getEmail());
        List<User> users = userService.getUserByQuery(user);
        request.getSession(true).setAttribute(CommonConstants.USER_ATTRIBUTE, users.get(0));
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();
    }

    @RequestMapping("accounts/changePassword")
    public String changePassword(String email, String password, String newPassword,
                                 String confirmPassword, ModelMap mode) {
        User auth = userService.auth(email, password);
        if (auth == null) {
            return "redirect:/accounts/profile?" + ResultMsg.errorMsg("密码错误").asUrlParams();
        }
        if (!confirmPassword.equals(newPassword)) {
            return "redirect:/accounts/profile?" + ResultMsg.errorMsg("两次输入密码不一致").asUrlParams();
        }
        UserVo userVo = new UserVo();
        userVo.setPasswd(HashUtils.encryPassword(newPassword));
        userService.updateUser(userVo, email);
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();
    }

    @RequestMapping("accounts/remember")
    public String remember(String username, ModelMap modelMap) {
        if (StringUtils.isBlank(username)) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("邮箱不能为空").asUrlParams();
        }
        userService.resetNotify(username);
        modelMap.put("email", username);
        return "/user/accounts/remember";
    }

    @RequestMapping("accounts/reset")
    public String reset(String key, ModelMap modelMap) {
        String resetEmail = userService.getResetEmail(key);
        if (StringUtils.isBlank(resetEmail)) {
            return "redirect:/accounts/signin?" + ResultMsg.errorMsg("重置链接已过期").asUrlParams();
        }
        modelMap.put("email", resetEmail);
        modelMap.put("success_key", key);
        return "/user/accounts/reset";
    }

    @RequestMapping(value = "accounts/resetSubmit")
    public String resetSubmit(HttpServletRequest request, UserVo userVo) {
        ResultMsg resultMsg = UserHelper.validateResetPassword(userVo.getKey(), userVo.getPasswd(), userVo.getConfirmPasswd());
        if (!resultMsg.isSuccess()) {
            String suffix = "";
            if (StringUtils.isNotBlank(userVo.getKey())) {
                suffix = "email=" + userService.getResetEmail(userVo.getKey()) + "&key=" + userVo.getKey() + "&";
            }
            return "redirect:/accounts/reset?" + suffix + resultMsg.asUrlParams();
        }
        User user = userService.reset(userVo.getKey(), userVo.getPasswd());
        request.getSession(true).setAttribute(CommonConstants.USER_ATTRIBUTE, user);
        return "redirect:/index?" + resultMsg.asUrlParams();
    }
}
