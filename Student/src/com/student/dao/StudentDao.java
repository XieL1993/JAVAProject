package com.student.dao;

import com.student.domin.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    List<Student> findAll()  throws SQLException;
    void addStudent(Student student)  throws SQLException;
}
