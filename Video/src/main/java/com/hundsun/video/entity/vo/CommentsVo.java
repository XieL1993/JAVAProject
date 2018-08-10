package com.hundsun.video.entity.vo;

import com.hundsun.video.entity.Comments;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommentsVo extends Comments {

    private String toUserName;

    private String toUserAvatar;

    private String fromUserName;

    private String fromUserAvatar;

}
