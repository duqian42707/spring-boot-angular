package com.dqv5.soccer.common;

import lombok.Builder;
import lombok.Data;

/**
 * 通用返回值实体类
 *
 * @author duq
 * @date 2022/7/17
 */
@Data
@Builder
public class RestReturnEntity<T> {
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 异常信息
     */
    private String errMsg;
    /**
     * 数据
     */
    private T data;


}

