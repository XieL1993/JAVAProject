package com.hundsun.video.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.hundsun.video.entity.Comments;
import com.hundsun.video.entity.vo.CommentsVo;
import com.hundsun.video.mapper.CommentsMapper;
import com.hundsun.video.service.ICommentsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程评论表 服务实现类
 * </p>
 *
 * @author xl
 * @since 2018-08-10
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {
    @Autowired
    private CommentsMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Page<CommentsVo> getAllComments(String videoId, Integer pageSize, Integer currentPage) {
        Page<CommentsVo> page = new Page<>(currentPage, pageSize);
        return page.setRecords(mapper.queryComments(page, videoId));
    }
}
