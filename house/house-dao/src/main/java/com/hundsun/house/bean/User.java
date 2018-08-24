package com.hundsun.house.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String aboutme;
    private String passwd;
    private String avatar;
    private Integer type;//普通用户1，经纪人2
    private Date createTime;
    private Integer enable;
    private Long agencyId;
}
