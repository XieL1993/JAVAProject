package com.hundsun.video.controller;

import com.hundsun.video.entity.Actor;
import com.hundsun.video.entity.Result;
import com.hundsun.video.service.IActorService;
import com.hundsun.video.utils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "actor", tags = {"演员接口"})
public class ActorController {

    @Autowired
    private IActorService service;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增演员", notes = "新增演员接口")
    public Result<Actor> add(Actor actor) {
        return ResultUtils.success(service.insert(actor));
    }
}
