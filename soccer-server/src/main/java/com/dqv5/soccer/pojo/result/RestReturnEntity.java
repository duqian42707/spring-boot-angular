package com.dqv5.soccer.pojo.result;

/**
 * 通用返回值实体类
 *
 * @author duq
 * @date 2022/7/17
 */
public class RestReturnEntity {
    /*提示信息*/
    private String msg;
    /*异常信息*/
    private String errMsg;
    /*数据*/
    private Object data;

    public RestReturnEntity() {
    }

    public RestReturnEntity(String msg, String errMsg, Object data) {
        this.msg = msg;
        this.errMsg = errMsg;
        this.data = data;
    }

    public static RestReturnBuilder builder() {
        return new RestReturnBuilder();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

