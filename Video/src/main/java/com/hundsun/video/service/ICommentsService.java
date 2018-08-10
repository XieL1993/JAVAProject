package com.hundsun.video.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hundsun.video.entity.Comments;
import com.hundsun.video.entity.vo.CommentsVo;

/**
 * <p>
 * 课程评论表 服务类
 * </p>
 *
 * @author xl
 * @since 2018-08-10
 */
public interface ICommentsService extends IService<Comments> {
    Page<CommentsVo> getAllComments(String videoId, Integer pageSize, Integer currentPage);
}
