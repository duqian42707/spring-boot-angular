package com.dqv5.soccer.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        QueryWrapper<SysRole> query1 = Wrappers.query(SysRole.class).eq("role_value", roleValue);
        if (sysRoleMapper.exists(query1)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        QueryWrapper<SysRole> query2 = Wrappers.query(SysRole.class).eq("role_name", roleName);
        if (sysRoleMapper.exists(query2)) {
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
        QueryWrapper<SysRole> query1 = Wrappers.query(SysRole.class).eq("role_value", roleValue).ne("role_id", roleId);
        if (sysRoleMapper.exists(query1)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        QueryWrapper<SysRole> query2 = Wrappers.query(SysRole.class).eq("role_name", roleName).ne("role_id", roleId);
        if (sysRoleMapper.exists(query2)) {
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
