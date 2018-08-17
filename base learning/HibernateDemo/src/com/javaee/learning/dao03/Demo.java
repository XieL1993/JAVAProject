package com.javaee.learning.dao03;

import com.javaee.learning.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class Demo {
    @Test
    public void add() {
        Student s1 = new Student("郑爽");
        Student s2 = new Student("杨幂");
        Course c1 = new Course("表演");
        Course c2 = new Course("唱歌");
        Course c3 = new Course("跳舞");
        s1.getCourses().add(c1);
        s1.getCourses().add(c2);
        s2.getCourses().add(c2);
        s2.getCourses().add(c3);
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(s1);
        session.save(s2);
        transaction.commit();
    }

    @Test
    public void addCourse() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, 1);
        Course course = new Course("健身");
        student.getCourses().add(course);
        transaction.commit();
    }
    @Test
    public void delete(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, 1);
        Course course = session.get(Course.class, 1);
        student.getCourses().remove(course);
        transaction.commit();
    }
    @Test
    public void Test(){
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, 1);
        System.out.println(student);
        transaction.commit();
    }
}
