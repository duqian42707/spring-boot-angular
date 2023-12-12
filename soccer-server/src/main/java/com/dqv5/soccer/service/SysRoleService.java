package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.Pageable;
import com.dqv5.soccer.pojo.SysRole;
import com.dqv5.soccer.table.SysRoleTable;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface SysRoleService {

    PageInfo<SysRoleTable> queryListForPage(Pageable pageable);

    SysRole findOne(String id);

    void insert(SysRoleTable param);

    void update(SysRoleTable param);

    void deleteById(String id);

    void saveRoleMenuAuth(SysRole param);

    List<SysRole> queryByUserId(String userId);
}
