package com.imooc.service;

import com.imooc.domain.Actor;

import java.util.List;

public interface ActorService {
    Actor add(Actor actor);

    void delete(Integer id);

    Actor update(Actor actor);

    Actor findById(Integer id);

    List<Actor> findAll();

    void getAge(Integer id);
}
