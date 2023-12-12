package com.dqv5.soccer.pojo;

import com.dqv5.soccer.table.AbstractBaseTable;
import com.dqv5.soccer.table.SysAuthTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("系统权限")
public class SysAuth extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限id")
    private String authId;

    @ApiModelProperty("权限标识")
    private String authValue;

    @ApiModelProperty("权限名称")
    private String authName;

    @ApiModelProperty("所属菜单id")
    private String menuId;

    @ApiModelProperty("排序")
    private Integer displayIndex;


    public static SysAuth of(SysAuthTable table) {
        SysAuth sysAuth = new SysAuth();
        BeanUtils.copyProperties(table, sysAuth);
        return sysAuth;
    }

    public SysAuthTable toTable() {
        SysAuthTable table = new SysAuthTable();
        BeanUtils.copyProperties(this, table);
        return table;
    }

}
