package com.hundsun.house.web.controller;

import com.hundsun.house.bean.User;
import com.hundsun.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        int a = 10 / 0;
        return "鞠婧祎";
    }

    @RequestMapping("/hello")
    public String hello(ModelMap modelMap) {
        List<User> users = userService.getUsers();
        User user = users.get(0);
        modelMap.put("user", user);
        return "hello";
    }

    @RequestMapping("/index")
    public String index() {
        return "homepage/index";
    }
}
