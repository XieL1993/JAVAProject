package com.javaee.learning.demo2.controller;

import com.javaee.learning.demo2.pojo.Actor;
import com.javaee.learning.demo2.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ActorController {
    @Resource(name = "actorService")
    private ActorService service;

    @RequestMapping("/actor")
    public String findAll(Model model) {
        List<Actor> actors = service.findAll();
        for (Actor actor : actors) {
            System.out.println(actor);
        }
        model.addAttribute("list", actors);
        return "actorList";
    }

    @RequestMapping("/actor/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addActor");
        return modelAndView;
    }

    @RequestMapping("/addActor")
    public String addActor(Actor actor, MultipartFile file) throws IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String picName = format.format(date);
        String oriName = file.getOriginalFilename();
        String extName = oriName.substring(oriName.indexOf("."));
        file.transferTo(new File("C:/Users/Administrator/Desktop/image/" + picName + extName));
        actor.setImage(picName + extName);
        service.addActor(actor);
        return "success";
    }
}
