package com.hundsun.video.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 背景音乐表
 * </p>
 *
 * @author xl
 * @since 2018-08-07
 */
@Data
@Accessors(chain = true)
public class Bgm extends Model<Bgm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 歌手
     */
    private String author;

    /**
     * 歌名
     */
    private String name;

    /**
     * 播放地址
     */
    private String path;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
