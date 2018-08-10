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
 * 课程评论表
 * </p>
 *
 * @author xl
 * @since 2018-08-10
 */
@Data
@Accessors(chain = true)
public class Comments extends Model<Comments> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer fatherCommentId;

    private Integer toUserId;

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 留言者，评论的用户id
     */
    private Integer fromUserId;

    /**
     * 评论内容
     */
    private String comment;

    private Date createTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
