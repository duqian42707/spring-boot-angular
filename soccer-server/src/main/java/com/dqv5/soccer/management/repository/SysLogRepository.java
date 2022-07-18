package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysLogRepository extends JpaRepository<SysLog, String> {
}
