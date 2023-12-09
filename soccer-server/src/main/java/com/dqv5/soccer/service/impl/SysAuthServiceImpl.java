package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.table.SysAuthTable;
import com.dqv5.soccer.mapper.SysAuthMapper;
import com.dqv5.soccer.service.SysAuthService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysAuthServiceImpl implements SysAuthService {
    @Resource
    private SysAuthMapper sysAuthMapper;

    @Override
    public List<SysAuthTable> findAll(String menuId) {
        QueryWrapper<SysAuthTable> queryWrapper = Wrappers.query(SysAuthTable.class).eq("menu_id", menuId);
        return sysAuthMapper.selectList(queryWrapper);
    }

    @Override
    public PageInfo<SysAuthTable> queryListForPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysAuthTable> list = sysAuthMapper.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public SysAuthTable findOne(String id) {
        return sysAuthMapper.selectById(id);
    }

    @Override
    public void insert(SysAuthTable param) {
        param.setAuthId(null);
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        String menuId = param.getMenuId();
        QueryWrapper<SysAuthTable> query1 = Wrappers.query(SysAuthTable.class).eq("auth_value", authValue).eq("menu_id", menuId);
        if (sysAuthMapper.exists(query1)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        QueryWrapper<SysAuthTable> query2 = Wrappers.query(SysAuthTable.class).eq("auth_name", authName).eq("menu_id", menuId);
        if (sysAuthMapper.exists(query2)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        sysAuthMapper.insert(param);
    }

    @Override
    public void update(SysAuthTable param) {
        String authId = param.getAuthId();
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        String menuId = param.getMenuId();
        SysAuthTable dataInDB = sysAuthMapper.selectById(authId);
        QueryWrapper<SysAuthTable> query1 = Wrappers.query(SysAuthTable.class).eq("auth_value", authValue).eq("menu_id", menuId).ne("auth_id", authId);
        if (sysAuthMapper.exists(query1)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        QueryWrapper<SysAuthTable> query2 = Wrappers.query(SysAuthTable.class).eq("auth_name", authName).eq("menu_id", menuId).ne("auth_id", authId);
        if (sysAuthMapper.exists(query2)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        dataInDB.setAuthValue(param.getAuthValue());
        dataInDB.setAuthName(param.getAuthName());
        dataInDB.setMenuId(param.getMenuId());
        sysAuthMapper.updateById(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysAuthMapper.deleteById(id);
    }

}
