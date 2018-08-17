package com.imooc.controller;

import com.imooc.domain.Actor;
import com.imooc.domain.Result;
import com.imooc.service.ActorService;
import com.imooc.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/actor/")
public class ActorController {
    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);
    @Autowired
    private ActorService service;

    @PostMapping(value = "add")
    public Result<Actor> add(@Valid Actor actor, BindingResult result) {
        if (result.hasErrors()) {
            return ResultUtils.error(1, result.getFieldError().getDefaultMessage());
        }
        logger.info("add");
        return ResultUtils.success(service.add(actor));
    }

    @GetMapping(value = "list")
    public List<Actor> findAll() {
        logger.info("findAll");
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Actor findById(@PathVariable(value = "id") Integer id) {
        logger.info("findById");
        return service.findById(id);
    }

    @GetMapping(value = "/age/{id}")
    public void getAge(@PathVariable(value = "id") Integer id) {
        service.getAge(id);
    }
}
