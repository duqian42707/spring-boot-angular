package com.dqv5.soccer.config;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.exception.CommonRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return RestReturn.fail(e.getMessage());
    }

    @ExceptionHandler(value = {CommonRuntimeException.class})
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(CommonRuntimeException e) {
        log.error("通用异常: {}", e.getMessage());
        return RestReturn.fail(e.getMessage());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(AccessDeniedException e) {
        log.error("{}", e.getMessage());
        RestReturnEntity<Object> body = RestReturnEntity.builder().msg("该操作禁止访问").build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }


}
