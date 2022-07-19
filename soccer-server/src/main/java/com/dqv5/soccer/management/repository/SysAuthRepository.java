package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysAuth;
import com.dqv5.soccer.management.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysAuthRepository extends JpaRepository<SysAuth, String> {

    List<SysAuth> findByMenu(SysMenu menu);

    boolean existsByAuthValueAndMenu(String authValue, SysMenu menu);

    boolean existsByAuthNameAndMenu(String authName, SysMenu menu);

    boolean existsByAuthValueAndMenuAndAuthIdNot(String authValue, SysMenu menu, String authId);

    boolean existsByAuthNameAndMenuAndAuthIdNot(String authName, SysMenu menu, String authId);
}
