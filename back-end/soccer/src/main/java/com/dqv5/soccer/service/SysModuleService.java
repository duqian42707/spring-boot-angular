package com.dqv5.soccer.service;

import com.dqv5.soccer.entity.SysModule;

import java.util.List;

/**
 * @author duq
 * @date 2018/8/18
 */
public interface SysModuleService {
    List<SysModule> findList();

    SysModule findOne(Integer id);

    SysModule save(SysModule sysModule);

    void delete(Integer id);

}
