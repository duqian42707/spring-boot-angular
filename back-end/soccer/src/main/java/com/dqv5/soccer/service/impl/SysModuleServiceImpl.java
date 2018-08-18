package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.entity.SysModule;
import com.dqv5.soccer.repository.SysModuleRepository;
import com.dqv5.soccer.service.SysModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duq
 * @date 2018/8/18
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {
    @Resource
    private SysModuleRepository sysModuleRepository;

    @Override
    public SysModule findOne(Integer id) {
        return sysModuleRepository.findOne(id);
    }

    @Override
    public SysModule save(SysModule sysModule) {
        return sysModuleRepository.save(sysModule);
    }

    @Override
    public void delete(Integer id) {
        sysModuleRepository.delete(id);
    }
}
