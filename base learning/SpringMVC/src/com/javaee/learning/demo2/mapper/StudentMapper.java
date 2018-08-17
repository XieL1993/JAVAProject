package com.javaee.learning.demo2.mapper;

import com.javaee.learning.demo2.pojo.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> findAll();

    int addStudent(Student student);
}
