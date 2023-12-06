package com.dqv5.soccer.management.service;

import com.dqv5.soccer.management.table.SysMenu;
import com.dqv5.soccer.service.BaseService;

import java.util.List;

/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysMenuService extends BaseService<SysMenu> {
    List<SysMenu> findAllTree();
}
