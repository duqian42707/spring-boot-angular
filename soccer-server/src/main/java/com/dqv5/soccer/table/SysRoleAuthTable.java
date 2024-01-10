package com.dqv5.soccer.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author duq
 * @date 2022/7/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_auth")
public class SysRoleAuthTable extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;

    private String roleId;
    private String authId;
}
