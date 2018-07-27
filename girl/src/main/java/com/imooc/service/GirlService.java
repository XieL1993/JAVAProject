package com.imooc.service;

import com.imooc.domain.Girls;
import com.imooc.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;
    @Transactional
    public void insertTwo(){
        Girls a = new Girls();
        a.setName("郑爽");
        a.setAge(24);
        girlRepository.save(a);
        Girls b = new Girls();
        b.setName("朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵朱茵");
        b.setAge(16);
        girlRepository.save(b);
    }
}
