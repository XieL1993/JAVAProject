package com.hundsun.video.service.impl;

import com.hundsun.video.entity.Actor;
import com.hundsun.video.mapper.ActorMapper;
import com.hundsun.video.service.IActorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 演员表 服务实现类
 * </p>
 *
 * @author xl
 * @since 2018-07-29
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements IActorService {

}
