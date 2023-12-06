package com.dqv5.soccer.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.management.table.SysMenu;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    boolean existsByMenuCode(String menuCode);

    boolean existsByMenuCodeAndMenuIdNot(String menuCode, String menuId);

    List<SysMenu> findAllByParentMenu(SysMenu parent);
}
