package com.dqv5.soccer.entity;

import java.util.Date;

/**
 * 角色
 *
 * @author duq
 * @date 2018/8/18
 */
public class SysRole {
    private Integer id;
    private String roleName;
    private Date createTime;
    private Date lastModifiedTime;
    private BasicUser createUser;
    private BasicUser modUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public BasicUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(BasicUser createUser) {
        this.createUser = createUser;
    }

    public BasicUser getModUser() {
        return modUser;
    }

    public void setModUser(BasicUser modUser) {
        this.modUser = modUser;
    }
}
