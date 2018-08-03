package com.hundsun.video.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hundsun.video.entity.VUser;
import com.hundsun.video.mapper.VUserMapper;
import com.hundsun.video.service.IVUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xl
 * @since 2018-07-30
 */
@Service
public class VUserServiceImpl extends ServiceImpl<VUserMapper, VUser> implements IVUserService {
    @Autowired
    private VUserMapper userMapper;

    @Override
    public VUser queryByOpenId(String openId) {
        VUser user = new VUser();
        user.setWeixinOpenid(openId);
        return userMapper.selectOne(user);
    }
}
