package com.student.dao;

import com.student.domin.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    List<Student> findAll(String searchText, int pageSize, int currentPage) throws SQLException;

    Student findStudentById(int sid) throws SQLException;

    void addStudent(Student student) throws SQLException;

    void updateStudent(Student student) throws SQLException;

    void deleteStudent(int sid) throws SQLException;

    int findCount(String searchText) throws SQLException;
}
