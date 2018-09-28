package com.cloud.demo.service;

import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String love(String name) {
        return "sorry " + name;
    }
}
