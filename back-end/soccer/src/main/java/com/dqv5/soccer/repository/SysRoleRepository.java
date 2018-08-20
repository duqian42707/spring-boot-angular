package com.dqv5.soccer.repository;

import com.dqv5.soccer.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @date 2018/8/20
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

}
