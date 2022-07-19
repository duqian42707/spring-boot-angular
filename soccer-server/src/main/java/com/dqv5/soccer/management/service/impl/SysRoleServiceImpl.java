package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.entity.SysRole;
import com.dqv5.soccer.management.repository.SysRoleRepository;
import com.dqv5.soccer.management.service.SysRoleService;
import com.dqv5.soccer.pojo.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author duq
 * @date 2022/7/18
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleRepository sysRoleRepository;

    @Override
    public PageInfo<SysRole> queryListForPage(Pageable pageable) {
        Page<SysRole> page = sysRoleRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysRole findOne(String id) {
        return sysRoleRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("角色id不存在:" + id));
    }

    @Override
    public void insert(SysRole param) {
        param.setRoleId(null);
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        if (sysRoleRepository.existsByRoleValue(roleValue)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        if (sysRoleRepository.existsByRoleName(roleName)) {
            throw new CommonRuntimeException("角色名称已存在：" + roleName);
        }
        sysRoleRepository.save(param);
    }

    @Override
    public void update(SysRole param) {
        String roleId = param.getRoleId();
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        SysRole dataInDB = sysRoleRepository.findById(roleId).orElseThrow(() -> new CommonRuntimeException("角色id不存在:" + roleId));
        if (sysRoleRepository.existsByRoleValueAndRoleIdNot(roleValue, roleId)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        if (sysRoleRepository.existsByRoleNameAndRoleIdNot(roleName, roleId)) {
            throw new CommonRuntimeException("角色名称已存在：" + roleName);
        }
        dataInDB.setRoleValue(roleValue);
        dataInDB.setRoleName(roleName);
        sysRoleRepository.save(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysRoleRepository.deleteById(id);
    }
}
