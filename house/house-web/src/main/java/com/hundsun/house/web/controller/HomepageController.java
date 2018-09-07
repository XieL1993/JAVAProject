package com.hundsun.house.web.controller;

import com.google.common.collect.Lists;
import com.hundsun.house.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
    @Autowired
    private RecommendService recommendService;

    @RequestMapping("")
    public String index(ModelMap modelMap) {
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String accountsRegister(ModelMap modelMap) {
        modelMap.put("recomHouses", recommendService.getLastest());
        return "/homepage/index";
    }
}
