package com.hundsun.house.service.impl;

import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.mapper.HouseMapper;
import com.hundsun.house.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Value("${file.prefix}")
    private String imgPrefix;
    @Autowired
    private HouseMapper houseMapper;

    public List<HouseVo> queryAndSetImg(HouseVo query, PageParams pageParams) {
        List<HouseVo> houses = houseMapper.selectPageHouses(query, pageParams);
        houses.forEach(h -> {
            h.setFirstImg(imgPrefix+h.getFirstImg());
//            h.setImageList(h.getImageList().stream().map())
        });
        return null;
    }
}
