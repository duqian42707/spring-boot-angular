package com.dqv5.soccer.entity;


/**
 * 角色权限
 *
 * @author duq
 * @date 2018/8/18
 */
public class SysRoleModule {
    private Integer id;
    private Integer roleId;
    private Integer moduleId;
    private Integer permValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getPermValue() {
        return permValue;
    }

    public void setPermValue(Integer permValue) {
        this.permValue = permValue;
    }
}
