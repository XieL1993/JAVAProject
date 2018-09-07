package com.hundsun.house.mapper;

import com.hundsun.house.bean.Agency;
import com.hundsun.house.bean.User;
import com.hundsun.house.bean.page.PageParams;
import com.hundsun.house.bean.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyMapper {
    List<Agency> select(Agency agency);

    int insert(Agency agency);

    List<UserVo> selectAgentUser(@Param("user") UserVo user, @Param("pageParams") PageParams pageParams);

    Long selectAgentCount(@Param("user") User user);

}
