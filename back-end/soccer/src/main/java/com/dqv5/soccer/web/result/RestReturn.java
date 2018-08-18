package com.dqv5.soccer.web.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author admin
 * @date 2018/7/17
 */
public class RestReturn {

    private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功！";
    private static final String DEFAULT_FAIL_MESSAGE = "服务器内部错误，操作失败！";

    public static ResponseEntity<RestReturnEntity> ok() {
        return build(true, DEFAULT_SUCCESS_MESSAGE);
    }

    public static ResponseEntity<RestReturnEntity> ok(String message) {
        return build(true, message);
    }

    public static ResponseEntity<RestReturnEntity> ok(Object data) {
        return build(true, DEFAULT_SUCCESS_MESSAGE, data);
    }

    public static ResponseEntity<RestReturnEntity> ok(String message, Object data) {
        return build(true, message, data);
    }

    public static ResponseEntity<RestReturnEntity> fail() {
        return build(false, DEFAULT_FAIL_MESSAGE);
    }

    public static ResponseEntity<RestReturnEntity> fail(String message) {
        return build(false, message);
    }

    public static ResponseEntity<RestReturnEntity> fail(String message, Object data) {
        return build(false, message, data);
    }

    public static ResponseEntity<RestReturnEntity> fail(Exception e) {
        return build(false, DEFAULT_FAIL_MESSAGE, null, e);
    }
    public static ResponseEntity<RestReturnEntity> fail(String message, Exception e) {
        return build(false, message, null, e);
    }

    public static ResponseEntity<RestReturnEntity> fail(String message, Object data, Exception e) {
        return build(false, message, data, e);
    }


    public static ResponseEntity<RestReturnEntity> build(boolean success, String message) {
        return build(success, message, null, null);
    }

    public static ResponseEntity<RestReturnEntity> build(boolean success, String message, Object data) {
        return build(success, message, data, null);
    }

    public static ResponseEntity<RestReturnEntity> build(boolean success, String message, Object data, Exception e) {
        if (success) {
            return build(HttpStatus.OK, message, data, e);
        } else {
            return build(HttpStatus.INTERNAL_SERVER_ERROR, message, data, e);
        }

    }


    public static ResponseEntity<RestReturnEntity> build(HttpStatus httpStatus, String message) {
        return build(httpStatus, message, null, null);
    }

    public static ResponseEntity<RestReturnEntity> build(HttpStatus httpStatus, String message, Object data) {
        return build(httpStatus, message, data, null);
    }

    public static ResponseEntity<RestReturnEntity> build(HttpStatus httpStatus, String message, Exception e) {
        return build(httpStatus, message, null, e);
    }

    public static ResponseEntity<RestReturnEntity> build(HttpStatus httpStatus, String message, Object data, Exception e) {
        if (e != null) {
            return ResponseEntity.status(httpStatus).body(RestReturnEntity.builder().msg(message).data(data).errMsg(e.getMessage()).build());
        } else {
            return ResponseEntity.status(httpStatus).body(RestReturnEntity.builder().msg(message).data(data).build());
        }

    }
}
