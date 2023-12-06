package com.dqv5.soccer.management.service.impl;

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
        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        return sysAuthMapper.findByMenu(menu);
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
        SysMenu menu = param.getMenu();
        if (sysAuthMapper.existsByAuthValueAndMenu(authValue, menu)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        if (sysAuthMapper.existsByAuthNameAndMenu(authName, menu)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        sysAuthMapper.insert(param);
    }

    @Override
    public void update(SysAuth param) {
        String authId = param.getAuthId();
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        SysMenu menu = param.getMenu();
        SysAuth dataInDB = sysAuthMapper.selectById(authId);
        if (sysAuthMapper.existsByAuthValueAndMenuAndAuthIdNot(authValue, menu, authId)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        if (sysAuthMapper.existsByAuthNameAndMenuAndAuthIdNot(authName, menu, authId)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        dataInDB.setAuthValue(param.getAuthValue());
        dataInDB.setAuthName(param.getAuthName());
        dataInDB.setMenu(param.getMenu());
        sysAuthMapper.updateById(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysAuthMapper.deleteById(id);
    }

}
