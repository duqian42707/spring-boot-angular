package com.dqv5.soccer.exception;

/**
 * @author duq
 * @date 2022/7/17
 */
public class CommonRuntimeException extends RuntimeException {
    public CommonRuntimeException() {
        super();
    }

    public CommonRuntimeException(String message) {
        super(message);
    }

    public CommonRuntimeException(Throwable cause) {
        super(cause);
    }
}
