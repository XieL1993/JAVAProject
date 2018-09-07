package com.hundsun.house.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Agency {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String aboutUs;
    private String mobile;
    private String webSite;
}
