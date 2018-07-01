package com.javaee.learning.service;

import com.javaee.learning.dao.Course;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CourseService {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        Course course = (Course) context.getBean("course");
        System.out.println(course);
    }
}
