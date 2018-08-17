package com.javaee.learning.demo2.service;

import com.javaee.learning.demo2.mapper.StudentMapper;
import com.javaee.learning.demo2.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> findAll() {
        List<Student> students = studentMapper.findAll();

        for (Student student : students) {
            System.out.println(student);
        }
        return students;
    }

    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

}
