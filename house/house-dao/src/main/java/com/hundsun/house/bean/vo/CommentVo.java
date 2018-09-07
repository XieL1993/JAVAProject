package com.hundsun.house.bean.vo;

import com.hundsun.house.bean.Comment;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommentVo extends Comment {
    private String userName;
    private String avatar;
}
