package com.imooc;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girls,Integer>{

    List<Girls> findByName(String name);
}
