package com.dqv5.soccer.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.table.SysAuth;
import com.dqv5.soccer.management.table.SysMenu;
import com.dqv5.soccer.management.mapper.SysAuthMapper;
import com.dqv5.soccer.management.service.SysAuthService;
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
    public List<SysAuth> findAll(String menuId) {
        QueryWrapper<SysAuth> queryWrapper = Wrappers.query(SysAuth.class).eq("menu_id", menuId);
        return sysAuthMapper.selectList(queryWrapper);
    }

    @Override
    public PageInfo<SysAuth> queryListForPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysAuth> list = sysAuthMapper.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public SysAuth findOne(String id) {
        return sysAuthMapper.selectById(id);
    }

    @Override
    public void insert(SysAuth param) {
        param.setAuthId(null);
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        String menuId = param.getMenuId();
        QueryWrapper<SysAuth> query1 = Wrappers.query(SysAuth.class).eq("auth_value", authValue).eq("menu_id", menuId);
        if (sysAuthMapper.exists(query1)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        QueryWrapper<SysAuth> query2 = Wrappers.query(SysAuth.class).eq("auth_name", authName).eq("menu_id", menuId);
        if (sysAuthMapper.exists(query2)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        sysAuthMapper.insert(param);
    }

    @Override
    public void update(SysAuth param) {
        String authId = param.getAuthId();
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        String menuId = param.getMenuId();
        SysAuth dataInDB = sysAuthMapper.selectById(authId);
        QueryWrapper<SysAuth> query1 = Wrappers.query(SysAuth.class).eq("auth_value", authValue).eq("menu_id", menuId).ne("auth_id", authId);
        if (sysAuthMapper.exists(query1)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        QueryWrapper<SysAuth> query2 = Wrappers.query(SysAuth.class).eq("auth_name", authName).eq("menu_id", menuId).ne("auth_id", authId);
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
