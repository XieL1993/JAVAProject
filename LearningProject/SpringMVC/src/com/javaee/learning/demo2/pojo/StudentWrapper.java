package com.javaee.learning.demo2.pojo;

public class StudentWrapper {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentWrapper{" +
                "student=" + student +
                '}';
    }
}
