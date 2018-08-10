package com.hundsun.video.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hundsun.video.entity.Video;
import com.hundsun.video.entity.VideoLinkUser;

/**
 * <p>
 * 视频信息表 服务类
 * </p>
 *
 * @author xl
 * @since 2018-08-06
 */
public interface IVideoService extends IService<Video> {
    Page<VideoLinkUser> getAllVideos(String userId, String videoDesc, Integer isSaveRecord,
                                     Integer pageSize, Integer currentPage);
}
