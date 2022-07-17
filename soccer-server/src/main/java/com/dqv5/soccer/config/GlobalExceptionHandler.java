package com.dqv5.soccer.config;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.pojo.result.RestReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {CommonRuntimeException.class})
    @ResponseBody
    public ResponseEntity commonJsonExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return RestReturn.fail(e.getMessage(), e);
    }

    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity jsonExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return RestReturn.fail(e);
    }

}
