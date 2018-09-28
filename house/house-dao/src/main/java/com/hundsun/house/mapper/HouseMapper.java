package com.hundsun.house.mapper;

import com.hundsun.house.bean.Community;
import com.hundsun.house.bean.HouseUser;
import com.hundsun.house.bean.UserMsg;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;
import com.hundsun.house.bean.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseMapper {
    Long selectPageCount(@Param("house") HouseVo houseVo);

    List<HouseVo> selectPageHouses(@Param("house") HouseVo houseVo, @Param("pageParams") PageParams pageParams);

    HouseUser selectHouseUser(@Param("userId") Long userId, @Param("houseId") Long houseId, @Param("type") Integer integer);

    HouseUser selectSaleHouseUser(@Param("houseId") Long houseId);

    int insertUserMsg(UserMsg userMsg);

    List<Community> selectCommunity(Community community);

    int insertHouseUser(HouseUser houseUser);

    int insert(HouseVo house);
}
