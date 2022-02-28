package com.wangc.springcloud.common;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 500);
        map.put("msg", e.getMessage());
        map.put("url", request.getRequestURL());
        return map;
    }
}
