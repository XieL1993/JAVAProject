package com.student.service.impl;

import com.student.dao.StudentDao;
import com.student.dao.impl.StudentDaoImpl;
import com.student.domin.Student;
import com.student.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService{
    @Override
    public List<Student> findAll() throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        return dao.findAll();
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.addStudent(student);
    }
}
