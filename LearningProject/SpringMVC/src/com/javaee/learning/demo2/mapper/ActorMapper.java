package com.javaee.learning.demo2.mapper;

import com.javaee.learning.demo2.pojo.Actor;

import java.util.List;

public interface ActorMapper {
    List<Actor> findAll();

    void addActor(Actor actor);
}
