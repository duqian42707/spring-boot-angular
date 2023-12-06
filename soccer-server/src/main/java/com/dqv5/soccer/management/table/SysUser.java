package com.dqv5.soccer.management.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dqv5.soccer.pojo.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author duq
 * @date 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_UUID)
    private String userId;
    private String account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String phone;
    private String email;
    private Integer status;
    private String openid;
    private Date lastLoginTime;
    private Date lastPasswordResetTime;
}
