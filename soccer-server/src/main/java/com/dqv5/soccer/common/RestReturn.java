package com.dqv5.soccer.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author duq
 * @date 2022/7/17
 */
public class RestReturn {

    private static final String SUCCESS = "操作成功";

    public static <T> ResponseEntity<RestReturnEntity<T>> ok() {
        return ResponseEntity.ok(RestReturnEntity.<T>builder().msg(SUCCESS).build());
    }

    public static <T> ResponseEntity<RestReturnEntity<T>> ok(String message) {
        return ResponseEntity.ok(RestReturnEntity.<T>builder().msg(message).build());
    }

    public static <T> ResponseEntity<RestReturnEntity<T>> ok(T data) {
        return ResponseEntity.ok(RestReturnEntity.<T>builder().msg(SUCCESS).data(data).build());
    }

    public static <T> ResponseEntity<RestReturnEntity<T>> ok(String message, T data) {
        return ResponseEntity.ok(RestReturnEntity.<T>builder().msg(message).data(data).build());
    }

    public static <T> ResponseEntity<RestReturnEntity<T>> fail(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestReturnEntity.<T>builder().msg(message).build());
    }

    public static <T> ResponseEntity<RestReturnEntity<T>> fail(String message, T data) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestReturnEntity.<T>builder().msg(message).data(data).build());
    }
}
