package com.dqv5.soccer.entity;


/**
 * 角色权限
 *
 * @author duq
 * @date 2018/8/18
 */
public class SysRoleModule {
    private Integer id;
    private SysRole role;
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
