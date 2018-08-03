package com.hundsun.video.entity;

public class WxLoginInfo {
    private String code;
    private UserInfo userInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "WxLoginInfo{" +
                "code='" + code + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
