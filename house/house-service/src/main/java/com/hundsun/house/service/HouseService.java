package com.hundsun.house.service;

import com.hundsun.house.bean.Community;
import com.hundsun.house.bean.HouseUser;
import com.hundsun.house.bean.User;
import com.hundsun.house.bean.UserMsg;
import com.hundsun.house.bean.page.PageData;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;

import java.util.List;

public interface HouseService {
    List<HouseVo> queryAndSetImg(HouseVo query, PageParams pageParams);

    PageData<HouseVo> queryHouse(HouseVo query, PageParams pageParams);

    HouseVo queryOneHouse(Long id);

    HouseUser getHouseUser(Long houseId);

    void addUserMsg(UserMsg userMsg);

    List<Community> getAllCommunitys();

    void addHouse(HouseVo house, User user);

    void bindUser2House(Long houseId, Long userId, boolean collect);
}
