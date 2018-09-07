package com.hundsun.house.mapper;

import com.hundsun.house.bean.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentVo> selectComments(@Param("houseId") long houseId, @Param("size") int size);
}
