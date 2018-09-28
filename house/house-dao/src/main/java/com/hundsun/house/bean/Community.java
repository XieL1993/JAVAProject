package com.hundsun.house.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Community {
    private Integer id;
    private String cityCode;
    private String cityName;
    private String name;
}
