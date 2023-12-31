package com.dqv5.soccer.service;

import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.table.SysMenuTable;

import java.util.List;


/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysMenuService {
    List<SysMenu> findAll();

    List<TreeNode> findAllTree();

    SysMenuTable findOne(String id);

    void insert(SysMenu param);

    void update(SysMenu param);

    void deleteById(String id);

    List<SysMenu> queryByUserId(String userId);
}
