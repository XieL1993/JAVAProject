package com.hundsun.video.utils;


import com.hundsun.video.entity.Result;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.exception.VException;

public class ResultUtils {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result error(ResultEnums enums) {
        return error(enums.getCode(), enums.getMsg());
    }
    public static Result error(VException e) {
        return error(e.getCode(), e.getMessage());
    }
}
