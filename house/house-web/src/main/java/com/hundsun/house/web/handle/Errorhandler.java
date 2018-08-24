package com.hundsun.house.web.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class Errorhandler {
    private static final Logger logger = LoggerFactory.getLogger(Errorhandler.class);

    @ExceptionHandler(value = Exception.class)
    public String handle(Exception e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        logger.error(request.getRequestURL() + " error 500");
        return "error/500";
    }
}
