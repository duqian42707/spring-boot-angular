package com.dqv5.soccer.dao;

import com.dqv5.soccer.entity.SysRole;
import com.dqv5.soccer.entity.SysRoleModule;

import java.util.List;

/**
 * @author duqian
 * @date 2018/9/5
 */
public interface SysRoleMapper {
    List<SysRole> findList();

    List<SysRoleModule> selectRoleModules(int roleId);

    List<SysRole> findListByUser(int userId);

    SysRole findOne(int id);

    void insert(SysRole sysRole);

    void update(SysRole sysRole);

    void delete(int id);


    void insertRoleModule(SysRoleModule roleModule);

    void deleteModules(int roleId);
}
