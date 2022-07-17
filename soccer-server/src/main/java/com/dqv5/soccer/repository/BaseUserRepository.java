package com.dqv5.soccer.repository;

import com.dqv5.soccer.pojo.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface BaseUserRepository extends JpaRepository<BaseUser, String> {
    Optional<BaseUser> findByAccount(String account);
}
