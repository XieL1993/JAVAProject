package com.hundsun.video.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hundsun.video.entity.Comments;
import com.hundsun.video.entity.Result;
import com.hundsun.video.entity.vo.CommentsVo;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.exception.VException;
import com.hundsun.video.service.ICommentsService;
import com.hundsun.video.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 课程评论表 前端控制器
 * </p>
 *
 * @author xl
 * @since 2018-08-10
 */
@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    @Autowired
    private ICommentsService commentsService;

    @PostMapping("/add")
    public Result saveComment(@RequestBody Comments comments) {
        comments.setCreateTime(new Date());
        commentsService.insert(comments);
        return ResultUtils.success();
    }

    @GetMapping("/list")
    public Result queryComments(String videoId, Integer pageSize, Integer currentPage) {
        if (pageSize == null || currentPage == null) {
            throw new VException(ResultEnums.BAD_PARAMS);
        }
        Page<CommentsVo> comments = commentsService.getAllComments(videoId, pageSize, currentPage);
        return ResultUtils.success(comments);
    }
}
