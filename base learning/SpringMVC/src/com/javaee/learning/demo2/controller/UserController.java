package com.javaee.learning.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) {
        System.out.println(username);
        System.out.println(password);
        session.setAttribute("username", username);
        return "redirect:/actor.action";
    }
}
