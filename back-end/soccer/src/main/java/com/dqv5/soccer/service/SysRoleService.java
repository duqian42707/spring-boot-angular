package com.dqv5.soccer.service;

import com.dqv5.soccer.entity.SysRole;

/**
 * @author duq
 * @date 2018/8/18
 */
public interface SysRoleService {
    SysRole findOne(Integer id);

    SysRole save(SysRole sysRole);

    void delete(Integer id);

}
