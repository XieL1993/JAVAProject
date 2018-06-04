package com.student.dao.impl;

import com.student.dao.StudentDao;
import com.student.domin.Student;
import com.student.utils.SourceFactory;
import com.student.utils.TextUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> findAll(String searchText, int pageSize, int currentPage) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        String sql = "select * from student where 1 = 1 ";
        List<Object> list = new ArrayList<>();
        if (!TextUtils.isEmpty(searchText)) {
            sql += " and sname like ?";
            list.add("%" + searchText + "%");
        }
        if (pageSize > 0 && currentPage > 0) {
            sql += " limit ? offset ?";
            list.add(pageSize);
            list.add((currentPage - 1) * pageSize);
        }
        return runner.query(sql, new BeanListHandler<>(Student.class), list.toArray());
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        return runner.query("select * from student where sid = ?", new BeanHandler<>(Student.class), sid);
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        runner.update("insert into student values(null,?,?,?,?,?,?)", student.getSname(), student.getGender(),
                student.getPhone(), student.getBirthday(), student.getHobby(), student.getInfo());
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        runner.update("update student set " +
                        "sname = ? ," +
                        "gender = ? ," +
                        "phone = ? ," +
                        "birthday = ? ," +
                        "hobby = ? ," +
                        "info = ? where sid = ?",
                student.getSname(),
                student.getGender(),
                student.getPhone(),
                student.getBirthday(),
                student.getHobby(),
                student.getInfo(),
                student.getSid()
        );
    }

    @Override
    public void deleteStudent(int sid) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        runner.update("delete from student where sid = ?", sid);
    }

    @Override
    public int findCount(String searchText) throws SQLException {
        QueryRunner runner = new QueryRunner(SourceFactory.getDataSource());
        String sql = "select count(*) from student where 1 = 1 ";
        List<Object> list = new ArrayList<>();
        if (!TextUtils.isEmpty(searchText)) {
            sql += " and sname like ?";
            list.add("%" + searchText + "%");
        }
        Long count = (Long) runner.query(sql, new ScalarHandler(), list.toArray());
        return count.intValue();
    }
}
