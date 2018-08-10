package com.hundsun.video.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.hundsun.video.entity.Video;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hundsun.video.entity.VideoLinkUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频信息表 Mapper 接口
 * </p>
 *
 * @author xl
 * @since 2018-08-06
 */
public interface VideoMapper extends BaseMapper<Video> {

    List<VideoLinkUser> queryAllVideos(Pagination page, @Param("userId") String userId, @Param("videoDesc") String videoDesc);

}
