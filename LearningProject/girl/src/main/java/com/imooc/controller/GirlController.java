package com.imooc.controller;

import com.imooc.repository.GirlRepository;
import com.imooc.service.GirlService;
import com.imooc.domain.Girls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/girl")
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;
    @Autowired
    private GirlService service;

    @GetMapping(value = "/list")
    public List<Girls> findAll() {
        return girlRepository.findAll();
    }

    @PostMapping(value = "/add")
    @Transactional
    public Girls add(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        Girls girls = new Girls();
        girls.setName(name);
        girls.setAge(age);
        return girlRepository.save(girls);
    }

    @GetMapping(value = "/{id}")
    public Girls find(@PathVariable("id") Integer id) {
        return girlRepository.findById(id).get();
    }

    @PutMapping(value = "/update/{id}")
    public Girls update(@PathVariable("id") Integer id, @RequestParam(value = "name") String name) {
        Girls girls = find(id);
        girls.setName(name);
        return girlRepository.save(girls);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    @GetMapping(value = "/name")
    public List<Girls> findByName(@RequestParam(value = "name") String name) {
        return girlRepository.findByName(name);
    }

    @PostMapping(value = "/test")
    public void insertTwo() {
        service.insertTwo();
    }
}
