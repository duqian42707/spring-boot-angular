package com.dqv5.soccer.entity;

import javax.persistence.*;

/**
 * 角色权限
 *
 * @author duq
 * @date 2018/8/18
 */
@Entity
@Table(name = "sys_role_module")
public class SysRoleModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private SysRole role;
    @ManyToOne
    private SysModule module;
    private Integer permValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysModule getModule() {
        return module;
    }

    public void setModule(SysModule module) {
        this.module = module;
    }

    public Integer getPermValue() {
        return permValue;
    }

    public void setPermValue(Integer permValue) {
        this.permValue = permValue;
    }
}
