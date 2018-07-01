package com.javaee.learning.service;

import com.javaee.learning.dao.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonService {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }
}
