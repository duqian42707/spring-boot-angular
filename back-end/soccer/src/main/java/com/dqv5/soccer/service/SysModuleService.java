package com.dqv5.soccer.service;

import com.dqv5.soccer.entity.SysModule;

/**
 * @author duq
 * @date 2018/8/18
 */
public interface SysModuleService {
    SysModule findOne(Integer id);

    SysModule save(SysModule sysModule);

    void delete(Integer id);

}
