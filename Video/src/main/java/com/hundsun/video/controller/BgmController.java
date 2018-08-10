package com.hundsun.video.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hundsun.video.entity.Result;
import com.hundsun.video.service.IBgmService;
import com.hundsun.video.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 背景音乐表 前端控制器
 * </p>
 *
 * @author xl
 * @since 2018-08-01
 */
@RestController
@RequestMapping("/api/bgm")
@Api(tags = {"BGM接口"})
public class BgmController {
    @Autowired
    private IBgmService service;

    @GetMapping(value = "/list")
    public Result findAll() {
        return ResultUtils.success(service.selectList(new EntityWrapper<>()));
    }


}
