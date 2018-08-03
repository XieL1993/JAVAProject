package com.hundsun.video.handle;

import com.hundsun.video.entity.Result;
import com.hundsun.video.enums.ResultEnums;
import com.hundsun.video.exception.VException;
import com.hundsun.video.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof VException) {
            VException girlException = (VException) e;
            return ResultUtils.error(girlException.getCode(), girlException.getMessage());
        } else {
            logger.error("[系统异常]{]", e);
            return ResultUtils.error(ResultEnums.UNKONW_ERROR);
        }
    }
}
