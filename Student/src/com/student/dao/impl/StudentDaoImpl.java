package com.student.dao.impl;

import com.student.dao.StudentDao;
import com.student.domin.Student;
import com.student.utils.SourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao{
    @Override
    public List<Student> findAll() throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        return runner.query("select * from student",new BeanListHandler<>(Student.class));
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        runner.update("insert into student values(null,?,?,?,?,?,?)", student.getSname(),student.getGender(),
                student.getPhone(),student.getBirthday(),student.getHobby(),student.getInfo());
    }
}
