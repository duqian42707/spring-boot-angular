package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.*;
import com.dqv5.soccer.common.Pageable;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.pojo.SysRole;
import com.dqv5.soccer.service.SysRoleService;
import com.dqv5.soccer.table.*;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author duq
 * @date 2022/7/18
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysAuthMapper sysAuthMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysRoleAuthMapper sysRoleAuthMapper;

    @Override
    public PageInfo<SysRole> queryListForPage(Pageable pageable) {
        List<SysRole> list = sysRoleMapper.queryList();
        return new PageInfo<>(list);
    }

    @Override
    public SysRole findOne(String roleId) {
        SysRoleTable sysRoleTable = sysRoleMapper.selectById(roleId);
        SysRole sysRole = SysRole.of(sysRoleTable);
        setMenuAndAuth(sysRole);
        return sysRole;
    }

    @Override
    public void insert(SysRoleTable param) {
        param.setRoleId(null);
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        QueryWrapper<SysRoleTable> query1 = Wrappers.query(SysRoleTable.class).eq("role_value", roleValue);
        if (sysRoleMapper.exists(query1)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        QueryWrapper<SysRoleTable> query2 = Wrappers.query(SysRoleTable.class).eq("role_name", roleName);
        if (sysRoleMapper.exists(query2)) {
            throw new CommonRuntimeException("角色名称已存在：" + roleName);
        }
        sysRoleMapper.insert(param);
    }

    @Override
    public void update(SysRoleTable param) {
        String roleId = param.getRoleId();
        String roleValue = param.getRoleValue();
        String roleName = param.getRoleName();
        SysRoleTable dataInDB = sysRoleMapper.selectById(roleId);
        QueryWrapper<SysRoleTable> query1 = Wrappers.query(SysRoleTable.class).eq("role_value", roleValue).ne("role_id", roleId);
        if (sysRoleMapper.exists(query1)) {
            throw new CommonRuntimeException("角色标识已存在：" + roleValue);
        }
        QueryWrapper<SysRoleTable> query2 = Wrappers.query(SysRoleTable.class).eq("role_name", roleName).ne("role_id", roleId);
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

    @Override
    public void saveRoleMenu(SysRole param) {
        String roleId = param.getRoleId();
        List<SysMenu> menus = param.getMenus();
        Map<String, Object> delMap = new HashMap<>();
        delMap.put("role_id", roleId);
        sysRoleMenuMapper.deleteByMap(delMap);
        menus.forEach(x -> {
            SysRoleMenuTable table = new SysRoleMenuTable();
            table.setRoleId(roleId);
            table.setMenuId(x.getMenuId());
            sysRoleMenuMapper.insert(table);
        });
    }

    @Override
    public void saveRoleAuth(SysRole param) {
        String roleId = param.getRoleId();
        List<SysAuth> auths = param.getAuths();
        Map<String, Object> delMap = new HashMap<>();
        delMap.put("role_id", roleId);
        sysRoleAuthMapper.deleteByMap(delMap);
        auths.forEach(x -> {
            SysRoleAuthTable table = new SysRoleAuthTable();
            table.setRoleId(roleId);
            table.setAuthId(x.getAuthId());
            sysRoleAuthMapper.insert(table);
        });
    }

    @Override
    public List<SysRole> queryByUserId(String userId) {
        List<SysRoleTable> sysRoleTables = sysRoleMapper.queryByUserId(userId);
        return sysRoleTables.stream()
                .map(SysRole::of)
                .peek(this::setMenuAndAuth)
                .collect(Collectors.toList());
    }


    private void setMenuAndAuth(SysRole sysRole) {
        String roleId = sysRole.getRoleId();
        List<SysMenuTable> menuTables = sysMenuMapper.queryByRoleId(roleId);
        List<SysMenu> menus = menuTables.stream().map(SysMenu::of).collect(Collectors.toList());
        List<SysAuthTable> authTables = sysAuthMapper.queryByRoleId(roleId);
        List<SysAuth> auths = authTables.stream().map(SysAuth::of).collect(Collectors.toList());
        sysRole.setMenus(menus);
        sysRole.setAuths(auths);
    }
}
