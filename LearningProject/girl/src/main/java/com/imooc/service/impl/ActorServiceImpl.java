package com.imooc.service.impl;

import com.imooc.enums.ResultEnums;
import com.imooc.exception.GirlException;
import com.imooc.domain.Actor;
import com.imooc.repository.ActorRepository;
import com.imooc.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorRepository repository;

    @Override
    public Actor add(Actor actor) {
        return repository.save(actor);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Actor update(Actor actor) {
        return repository.save(actor);
    }

    @Override
    public Actor findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Actor> findAll() {
        return repository.findAll();
    }

    @Override
    public void getAge(Integer id) {
        Actor actor = repository.findById(id).get();
        Integer age = actor.getAge();
        if (age < 10) {
            throw new GirlException(ResultEnums.primary_school);
        } else if (age > 10 && age < 16) {
            throw new GirlException(ResultEnums.middle_school);
        }
    }
}
