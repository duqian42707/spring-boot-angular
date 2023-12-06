package com.dqv5.soccer.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.management.table.SysRole;

import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    boolean existsByRoleValue(String roleValue);

    boolean existsByRoleValueAndRoleIdNot(String roleValue, String roleId);

    boolean existsByRoleName(String roleName);

    boolean existsByRoleNameAndRoleIdNot(String roleName, String roleId);

    Optional<SysRole> findByRoleValue(String roleValue);
}
