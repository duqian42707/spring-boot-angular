package com.dqv5.soccer.config;

import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.common.RestReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return RestReturn.fail(e.getMessage());
    }

    @ExceptionHandler(value = {CommonRuntimeException.class})
    @ResponseBody
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(CommonRuntimeException e) {
        log.error("通用异常: {}", e.getMessage());
        return RestReturn.fail(e.getMessage());
    }


}
