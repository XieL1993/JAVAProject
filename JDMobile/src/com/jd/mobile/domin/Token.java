package com.jd.mobile.domin;

import java.util.Date;

public class Token {
    private String tid;
    private String username;
    private Date time;

    public Token() {
    }

    public Token(String tid, String username, Date time) {
        this.tid = tid;
        this.username = username;
        this.time = time;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
