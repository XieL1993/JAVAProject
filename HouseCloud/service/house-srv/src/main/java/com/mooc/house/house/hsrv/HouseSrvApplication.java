package com.mooc.house.house.hsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HouseSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseSrvApplication.class, args);
    }
}
