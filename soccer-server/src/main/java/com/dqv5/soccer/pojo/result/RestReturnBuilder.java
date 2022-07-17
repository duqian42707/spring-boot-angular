package com.dqv5.soccer.pojo.result;

/**
 * @author admin
 * @date 2022/7/17
 */
public class RestReturnBuilder {
    private String msg;
    private String errMsg;
    private Object data;

    public RestReturnBuilder msg(String msg) {
        this.msg = msg;
        return this;
    }

    public RestReturnBuilder errMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public RestReturnBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public RestReturnEntity build() {
        return new RestReturnEntity(this.msg, this.errMsg, this.data);
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
