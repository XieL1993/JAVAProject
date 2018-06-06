package com.student.domin;

import java.util.Date;

public class Token {
    private String token;
    private Date expiry;

    public Token() {
    }

    public Token(String token, Date expiry) {
        this.token = token;
        this.expiry = expiry;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

}
