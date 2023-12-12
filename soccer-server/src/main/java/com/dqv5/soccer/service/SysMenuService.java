package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.Pageable;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.table.SysMenuTable;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysMenuService {
    List<SysMenu> findAllTree();

    SysMenuTable findOne(String id);

    void insert(SysMenu param);

    void update(SysMenu param);

    void deleteById(String id);

    List<SysMenu> queryByUserId(String userId);
}
