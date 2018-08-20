package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.entity.SysRole;
import com.dqv5.soccer.repository.SysRoleRepository;
import com.dqv5.soccer.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 * @date 2018/8/20
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleRepository sysRoleRepository;

    @Override
    public SysRole findOne(Integer id) {
        return sysRoleRepository.findOne(id);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        return sysRoleRepository.save(sysRole);
    }

    @Override
    public void delete(Integer id) {
        sysRoleRepository.delete(id);
    }
}
