package com.student.service;

import com.student.domin.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    List<Student> findAll()  throws SQLException;

    void addStudent(Student student) throws SQLException;
}
