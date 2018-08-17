package com.javaee.learning.demo2.service;

import com.javaee.learning.demo2.mapper.ActorMapper;
import com.javaee.learning.demo2.pojo.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("actorService")
public class ActorService {
    @Autowired
    private ActorMapper mapper;

    public List<Actor> findAll() {
        return mapper.findAll();
    }

    public void addActor(Actor actor) {
        mapper.addActor(actor);
    }
}
