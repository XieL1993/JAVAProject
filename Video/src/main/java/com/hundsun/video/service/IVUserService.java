package com.hundsun.video.service;

import com.hundsun.video.entity.VUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xl
 * @since 2018-07-30
 */
public interface IVUserService extends IService<VUser> {
    VUser queryByOpenId(String openId);
}
