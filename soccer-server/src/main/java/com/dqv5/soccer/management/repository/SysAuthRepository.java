package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysAuthRepository extends JpaRepository<SysAuth, String> {
}
