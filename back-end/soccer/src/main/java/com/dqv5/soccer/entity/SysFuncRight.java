package com.dqv5.soccer.entity;


/**
 * 功能权限
 *
 * @author duq
 * @date 2018/8/18
 */
public class SysFuncRight {
    private Integer id;
    private String funcName;
    private String code;
    private Integer permValue;
    private SysModule module;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPermValue() {
        return permValue;
    }

    public void setPermValue(Integer permValue) {
        this.permValue = permValue;
    }

    public SysModule getModule() {
        return module;
    }

    public void setModule(SysModule module) {
        this.module = module;
    }
}
