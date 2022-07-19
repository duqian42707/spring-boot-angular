package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.entity.SysAuth;
import com.dqv5.soccer.management.entity.SysMenu;
import com.dqv5.soccer.management.repository.SysAuthRepository;
import com.dqv5.soccer.management.service.SysAuthService;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.pojo.TreeNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;

@Service
public class SysAuthServiceImpl implements SysAuthService {
    @Resource
    private SysAuthRepository sysAuthRepository;

    @Override
    public List<SysAuth> findAll(String menuId) {
        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        return sysAuthRepository.findByMenu(menu);
    }

    @Override
    public PageInfo<SysAuth> queryListForPage(Pageable pageable) {
        Page<SysAuth> page = sysAuthRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysAuth findOne(String id) {
        return sysAuthRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("菜单id不存在:" + id));
    }

    @Override
    public void insert(SysAuth param) {
        param.setAuthId(null);
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        SysMenu menu = param.getMenu();
        if (sysAuthRepository.existsByAuthValueAndMenu(authValue, menu)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        if (sysAuthRepository.existsByAuthNameAndMenu(authName, menu)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        sysAuthRepository.save(param);
    }

    @Override
    public void update(SysAuth param) {
        String authId = param.getAuthId();
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        SysMenu menu = param.getMenu();
        SysAuth dataInDB = sysAuthRepository.findById(authId).orElseThrow(() -> new CommonRuntimeException("权限id不存在:" + authId));
        if (sysAuthRepository.existsByAuthValueAndMenuAndAuthIdNot(authValue, menu, authId)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        if (sysAuthRepository.existsByAuthNameAndMenuAndAuthIdNot(authName, menu, authId)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        dataInDB.setAuthValue(param.getAuthValue());
        dataInDB.setAuthName(param.getAuthName());
        dataInDB.setMenu(param.getMenu());
        sysAuthRepository.save(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysAuthRepository.deleteById(id);
    }

}
