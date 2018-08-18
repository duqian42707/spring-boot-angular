package com.dqv5.soccer.repository;

import com.dqv5.soccer.entity.SysModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author duq
 * @date 2018/8/18
 */
@Repository
public interface SysModuleRepository extends JpaRepository<SysModule, Integer> {
}
