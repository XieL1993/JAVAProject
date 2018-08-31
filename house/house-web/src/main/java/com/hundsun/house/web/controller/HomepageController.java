package com.hundsun.house.web.controller;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
    @RequestMapping("")
    public String index(ModelMap modelMap) {
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String accountsRegister(ModelMap modelMap) {
        modelMap.put("recomHouses", Lists.newArrayList());
        return "/homepage/index";
    }
}
