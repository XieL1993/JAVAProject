package com.hundsun.house.bean.vo;

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

    private Integer roundRating = 0;
    private List<String> imageList = Lists.newArrayList();
    private List<String> floorPlanList = Lists.newArrayList();
    private List<String> featureList = Lists.newArrayList();
    private List<MultipartFile> houseFiles;
    private List<MultipartFile> floorPlanFiles;
    private String priceStr;
    private String typeStr;
    private Long userId;
    private boolean bookmarked;
}
