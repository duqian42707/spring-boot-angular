package com.dqv5.soccer.pojo;

import com.dqv5.soccer.table.AbstractBaseTable;
import com.dqv5.soccer.table.SysRoleTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
@ApiModel("系统角色")
public class SysRole extends AbstractBaseVO implements Serializable {
    @ApiModelProperty("角色id")
    private String roleId;
    @ApiModelProperty("角色标识")
    private String roleValue;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("关联的菜单")
    private List<SysMenu> menus = new ArrayList<>();
    @ApiModelProperty("关联的权限")
    private List<SysAuth> auths = new ArrayList<>();

    public static SysRole of(SysRoleTable table) {
        SysRole sysAuth = new SysRole();
        BeanUtils.copyProperties(table, sysAuth);
        return sysAuth;
    }

    public SysRoleTable toTable() {
        SysRoleTable table = new SysRoleTable();
        BeanUtils.copyProperties(this, table);
        return table;
    }


}
