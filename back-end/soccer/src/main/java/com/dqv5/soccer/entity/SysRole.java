package com.dqv5.soccer.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色
 *
 * @author duq
 * @date 2018/8/18
 */
@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private Date createTime;
    private Date lastModifiedTime;
    @ManyToOne
    private BasicUser createUser;
    @ManyToOne
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
