package com.hundsun.house.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class House {
    private Long id;
    private String name;
    private Integer type;// 1:销售，2:出租
    private Integer price;
    private String images;
    private Integer area;// 面积
    private Integer beds;// 卧室数量
    private Integer baths;// 卫生间数量
    private Double rating;// 评级
    private String remarks;
    private String properties;// 属性
    private String floorPlan;//户型图
    private String tags;// 标签
    private Date createTime;
    private String cityId;
    private String communityId;// 小区名称
    private String address;
    private Integer state;// 1-上架，2-下架
}
