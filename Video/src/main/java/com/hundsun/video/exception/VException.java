package com.hundsun.video.exception;


import com.hundsun.video.enums.ResultEnums;

public class VException extends RuntimeException {
    private Integer code;

    public VException(ResultEnums enums) {
        super(enums.getMsg());
        this.code = enums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
