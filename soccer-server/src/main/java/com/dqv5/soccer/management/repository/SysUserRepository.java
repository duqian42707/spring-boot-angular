package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysUserRepository extends JpaRepository<SysUser, String> {
    Optional<SysUser> findByAccount(String account);
}
