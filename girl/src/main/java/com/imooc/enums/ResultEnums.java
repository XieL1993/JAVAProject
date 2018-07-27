package com.imooc.enums;

public enum ResultEnums {
    UNKONW_ERROR(-1, "未知错误"),
    success(0, "成功"),
    primary_school(100, "你还在上小学吧"),
    middle_school(101, "你可能在上初中");
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
