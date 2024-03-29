package com.dqv5.soccer.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author duq
 * @date 2023/12/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_role")
public class SysUserRoleTable extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String roleId;
}
