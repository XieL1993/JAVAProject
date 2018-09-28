package com.cloud.demo.web;

import com.cloud.demo.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HiController {
    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "/love")
    public String sayHi(String name) {
        return schedualServiceHi.love(name);
    }
}
