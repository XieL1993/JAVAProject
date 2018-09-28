package com.hundsun.house.service.impl;

import com.google.common.collect.Lists;
import com.hundsun.house.bean.City;
import com.hundsun.house.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Override
    public List<City> getAllCitys() {
        City city = new City();
        city.setCityCode("110000");
        city.setCityName("北京");
        city.setId(1);
        return Lists.newArrayList(city);
    }
}
