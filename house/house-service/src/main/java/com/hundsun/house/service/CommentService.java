package com.hundsun.house.service;

import com.hundsun.house.bean.vo.CommentVo;

import java.util.List;

public interface CommentService {
    List<CommentVo> getHouseComments(long houseId, int size);
}
