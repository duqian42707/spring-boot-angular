package com.dqv5.soccer.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
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
@Schema(name = "系统角色")
public class SysRole extends AbstractBaseVO implements Serializable {
    @SchemaProperty(name = "角色id")
    private String roleId;
    @SchemaProperty(name = "角色标识")
    private String roleValue;
    @SchemaProperty(name = "角色名称")
    private String roleName;
    @SchemaProperty(name = "关联的菜单")
    private List<SysMenu> menus = new ArrayList<>();
    @SchemaProperty(name = "关联的权限")
    private List<SysAuth> auths = new ArrayList<>();


}
