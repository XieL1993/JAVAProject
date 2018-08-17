package com.javaee.learning.demo2.utils;

public class MyException extends Exception {
    private String message;

    public MyException() {
        super();
    }

    public MyException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
