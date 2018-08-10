package com.hundsun.video.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 视频信息表,联合用户信息
 * </p>
 *
 * @author xl
 * @since 2018-08-07
 */
@Data
@Accessors(chain = true)
public class VideoLinkUser extends Model<VideoLinkUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发布者id
     */
    private Integer userId;

    /**
     * 用户使用音频的信息
     */
    private Integer audioId;

    /**
     * 视频描述
     */
    private String videoDesc;

    /**
     * 视频存放的路径
     */
    private String videoPath;

    /**
     * 视频秒数
     */
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    private Integer videoWidth;

    /**
     * 视频高度
     */
    private Integer videoHeight;

    /**
     * 视频封面图
     */
    private String coverPath;

    /**
     * 喜欢/赞美的数量
     */
    private Long likeCounts;

    /**
     * 视频状态：
     * 1、发布成功
     * 2、禁止播放，管理员操作
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户昵称或网络名称
     */
    private String nickname;

    /**
     * 用户头像图片
     */
    private String avatar;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
