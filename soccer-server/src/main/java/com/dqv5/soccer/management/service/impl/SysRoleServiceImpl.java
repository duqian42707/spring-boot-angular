package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.table.SysRole;
import com.dqv5.soccer.management.mapper.SysRoleMapper;
import com.dqv5.soccer.management.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> queryListForPage(Pageable pageable) {
        List<SysRole> list = sysRoleMapper.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public SysRole findOne(String id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public void insert(SysRole param) {
        param.setRoleId(null);
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        if (sysRoleMapper.existsByRoleValue(roleValue)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        if (sysRoleMapper.existsByRoleName(roleName)) {
            throw new CommonRuntimeException("角色名称已存在：" + roleName);
        }
        sysRoleMapper.insert(param);
    }

    @Override
    public void update(SysRole param) {
        String roleId = param.getRoleId();
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        SysRole dataInDB = sysRoleMapper.selectById(roleId);
        if (sysRoleMapper.existsByRoleValueAndRoleIdNot(roleValue, roleId)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        if (sysRoleMapper.existsByRoleNameAndRoleIdNot(roleName, roleId)) {
            throw new CommonRuntimeException("角色名称已存在：" + roleName);
        }
        dataInDB.setRoleValue(roleValue);
        dataInDB.setRoleName(roleName);
        sysRoleMapper.updateById(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysRoleMapper.deleteById(id);
    }
}
