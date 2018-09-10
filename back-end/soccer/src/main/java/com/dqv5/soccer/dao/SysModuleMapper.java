package com.dqv5.soccer.dao;

import com.dqv5.soccer.entity.SysModule;

import java.util.List;

/**
 * @author duq
 * @date 2018/9/3
 */
public interface SysModuleMapper {
    List<SysModule> findList();

    List<SysModule> findListByUser(int userId);

    SysModule findOne(Integer id);

    void insert(SysModule sysModule);

    void update(SysModule sysModule);

    void delete(Integer id);
}
