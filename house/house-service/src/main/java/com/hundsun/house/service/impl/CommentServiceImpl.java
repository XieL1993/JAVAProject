package com.hundsun.house.service.impl;

import com.hundsun.house.bean.User;
import com.hundsun.house.bean.vo.CommentVo;
import com.hundsun.house.mapper.CommentMapper;
import com.hundsun.house.service.CommentService;
import com.hundsun.house.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;

    @Override
    public List<CommentVo> getHouseComments(long houseId, int size) {
        List<CommentVo> list = commentMapper.selectComments(houseId, size);
        list.forEach(comment -> {
            User user = userService.getUserById(comment.getUserId());
            comment.setAvatar(user.getAvatar());
            comment.setUserName(user.getName());
        });
        return list;
    }
}
