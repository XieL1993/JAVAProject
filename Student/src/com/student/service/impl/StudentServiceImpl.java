package com.student.service.impl;

import com.student.dao.StudentDao;
import com.student.dao.impl.StudentDaoImpl;
import com.student.domin.PageBean;
import com.student.domin.Student;
import com.student.service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    @Override
    public PageBean<Student> findAll(String searchText, int pageSize, int currentPage) throws SQLException {
        PageBean<Student> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        StudentDao dao = new StudentDaoImpl();
        int count = dao.findCount(searchText);
        pageBean.setTotalSize(count);
        pageBean.setTotalPage(count % pageSize == 0 ? count / pageSize : count / pageSize + 1);
        List<Student> students = dao.findAll(searchText, pageSize, currentPage);
        pageBean.setList(students);
        return pageBean;
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        return dao.findStudentById(sid);
    }

    @Override
    public void addStudent(Student student) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.updateStudent(student);
    }

    @Override
    public void deleteStudent(int sid) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.deleteStudent(sid);
    }
}
