package com.imooc.exception;

import com.imooc.enums.ResultEnums;

public class GirlException extends RuntimeException {
    private Integer code;

    public GirlException(ResultEnums enums) {
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
