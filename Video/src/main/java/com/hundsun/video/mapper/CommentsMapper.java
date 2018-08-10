package com.hundsun.video.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hundsun.video.entity.Comments;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hundsun.video.entity.vo.CommentsVo;

import java.util.List;

/**
 * <p>
 * 课程评论表 Mapper 接口
 * </p>
 *
 * @author xl
 * @since 2018-08-10
 */
public interface CommentsMapper extends BaseMapper<Comments> {
    public List<CommentsVo> queryComments(Pagination page, String videoId);
}
