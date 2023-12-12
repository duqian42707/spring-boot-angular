package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.Pageable;
import com.dqv5.soccer.pojo.SysRole;
import com.dqv5.soccer.table.SysRoleTable;
import com.github.pagehelper.PageInfo;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface SysRoleService {

    PageInfo<SysRoleTable> queryListForPage(Pageable pageable);

    SysRoleTable findOne(String id);

    void insert(SysRoleTable param);

    void update(SysRoleTable param);

    void deleteById(String id);

    SysRole getRoleMenuAuth(String roleId);

    void saveRoleMenuAuth(SysRole param);
}
