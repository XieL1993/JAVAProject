package com.hundsun.video.enums;

public enum ResultEnums {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    BAD_PARAMS(101, "Illegal params"),
    LOGIN_FAILED(102, "登录失败"),
    UN_LOGOIN(103, "Missing token,please login first"),
    ERROR_USER(104, "User does not exist"),
    ILLEGAL_TOKEN(105, "Illegal token,please try to login again");
    private Integer code;
    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
