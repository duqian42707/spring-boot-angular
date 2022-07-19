package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, String> {
    boolean existsByMenuCode(String menuCode);

    boolean existsByMenuCodeAndMenuIdNot(String menuCode, String menuId);
}
