package com.student.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SourceFactory {
    /*    CREATE DATABASE student
        USE student
        CREATE TABLE student (
                sid INT PRIMARY KEY AUTO_INCREMENT,
                sname VARCHAR(20),
                gender VARCHAR(5),
                phone VARCHAR(20),
                birthday DATE,
                hobby VARCHAR(50),
                info VARCHAR(200)

    )*/
    /*CREATE TABLE t_user (uid INT PRIMARY KEY AUTO_INCREMENT , username VARCHAR(20) , PASSWORD VARCHAR(20) )*/
//    CREATE TABLE t_token(token VARCHAR(50),expiry DATE)
    static ComboPooledDataSource dataSource = null;

    static {
        dataSource = new ComboPooledDataSource();
    }

    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }
}
