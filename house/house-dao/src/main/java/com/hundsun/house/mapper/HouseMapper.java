package com.hundsun.house.mapper;

import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.HouseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseMapper {
    Long selectPageCount(@Param("house") HouseVo houseVo);

    List<HouseVo> selectPageHouses(@Param("house") HouseVo houseVo, @Param("pageParams") PageParams pageParams);
}
