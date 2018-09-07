package com.hundsun.house.bean.vo;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hundsun.house.bean.House;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Accessors(chain = true)
public class HouseVo extends House {
    private List<Long> ids;// 同时传入多个house的id
    private String sort = "time_desc";//price_desc,price_asc,time_desc 排序
    private String firstImg;// 封面图片(使用图片列表中的第一张)
    private List<String> imageList = Lists.newArrayList();// 图片列表
    private List<MultipartFile> houseFiles;
    private List<String> floorPlanList = Lists.newArrayList();// 户型图
    private List<MultipartFile> floorPlanFiles;
    private String typeStr;// 类型描述
    private String priceStr;// 带单位的价格
    private List<String> featureList = Lists.newArrayList();//属性list
    private Integer roundRating = 0;// 四舍五入的评分

    private Long userId;// 关联用户id
    private boolean bookmarked;// 1 销售 2 出租

    @Override
    public House setType(Integer type) {
        if (type.equals(1)) {
            if (type.equals(1)) {
                this.typeStr = "For Sale";
            } else {
                this.typeStr = "For Rent";
            }
        }
        return super.setType(type);
    }

    @Override
    public House setPrice(Integer price) {
        this.priceStr = price + "万";
        return super.setPrice(price);
    }

    @Override
    public House setFloorPlan(String floorPlan) {
        if (!Strings.isNullOrEmpty(floorPlan)) {
            this.floorPlanList = Splitter.on(",").splitToList(floorPlan);
        }
        return super.setFloorPlan(floorPlan);
    }

    @Override
    public House setProperties(String properties) {
        if (!Strings.isNullOrEmpty(properties)) {
            this.featureList = Splitter.on(",").splitToList(properties);
        }
        return super.setProperties(properties);
    }

    @Override
    public House setImages(String images) {
        if (!Strings.isNullOrEmpty(images)) {
            List<String> list = Splitter.on(",").splitToList(images);
            if (list.size() > 0) {
                this.firstImg = list.get(0);
                this.imageList = list;
            }
        }
        return super.setImages(images);
    }

    @Override
    public House setRating(Double rating) {
        this.roundRating = (int) Math.round(rating);
        return super.setRating(rating);
    }

    public void setFeatureList(List<String> featureList) {
        this.featureList = featureList;
        super.setProperties(Joiner.on(",").join(featureList));
    }
}
