package com.dqv5.soccer.repository;

import com.dqv5.soccer.pojo.entity.BaseRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface BaseRoleRepository extends JpaRepository<BaseRole, String> {
    List<BaseRole> findByRoleIdIn(List<String> roleIds);

    Optional<BaseRole> findByRoleValue(String roleValue);
}
