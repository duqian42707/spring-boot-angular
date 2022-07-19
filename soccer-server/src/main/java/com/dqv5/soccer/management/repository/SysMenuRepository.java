package com.dqv5.soccer.management.repository;

import com.dqv5.soccer.management.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, String> {
    boolean existsByMenuCode(String menuCode);

    boolean existsByMenuCodeAndMenuIdNot(String menuCode, String menuId);

    List<SysMenu> findAllByParentMenu(SysMenu parent);
}
