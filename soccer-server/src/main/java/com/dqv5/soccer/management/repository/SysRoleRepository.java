package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysRoleRepository extends JpaRepository<SysRole, String> {

    boolean existsByRoleValue(String roleValue);

    boolean existsByRoleValueAndRoleIdNot(String roleValue, String roleId);

    boolean existsByRoleName(String roleName);

    boolean existsByRoleNameAndRoleIdNot(String roleName, String roleId);

    Optional<SysRole> findByRoleValue(String roleValue);
}
