package com.dqv5.soccer.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author admin
 * @date 2018/5/28
 */
public class SysLog implements Serializable {
    private Integer id;
    private BasicUser user;
    private String detail;
    private String ip;
    private Date operateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BasicUser getUser() {
        return user;
    }

    public void setUser(BasicUser user) {
        this.user = user;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
