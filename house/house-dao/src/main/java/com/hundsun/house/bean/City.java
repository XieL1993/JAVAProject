package com.hundsun.house.bean;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class City {
    private Integer id;
    private String cityName;
    private String cityCode;
}
