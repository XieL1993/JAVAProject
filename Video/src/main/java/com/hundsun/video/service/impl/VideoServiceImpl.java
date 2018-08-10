package com.hundsun.video.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hundsun.video.entity.Video;
import com.hundsun.video.entity.VideoLinkUser;
import com.hundsun.video.mapper.VideoMapper;
import com.hundsun.video.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 视频信息表 服务实现类
 * </p>
 *
 * @author xl
 * @since 2018-08-06
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Page<VideoLinkUser> getAllVideos(String userId, String videoDesc, Integer isSaveRecord, Integer pageSize, Integer currentPage) {
        Page<VideoLinkUser> page = new Page<>(currentPage, pageSize);
        return page.setRecords(videoMapper.queryAllVideos(page, userId, videoDesc));
    }
}
