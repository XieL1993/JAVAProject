package com.hundsun.house.service;

import com.hundsun.house.bean.vo.HouseVo;

import java.util.List;

public interface RecommendService {
    void increase(Long id);

    List<HouseVo> getHotHouse(Integer size);

    List<HouseVo> getLastest();
}
