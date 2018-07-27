package com.imooc.controller;

import com.imooc.properties.GirlProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloController {
    @Resource(name = "GirlProperties")
    private GirlProperties girl;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        return girl.say();
    }
    @GetMapping(value = "/favor")
    public String favor(@RequestParam(value = "a",required = false,defaultValue = "郑爽") String name){
        return "name:"+name;
    }
}
