package com.dqv5.soccer.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author duqian
 * @date 2023/12/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "系统用户")
public class SysUser extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String phone;
    private String email;
    private Integer status;
    private String openid;
    private Date lastLoginTime;
    private Date lastPasswordResetTime;

    private List<SysDept> depts = new ArrayList<>();


}
