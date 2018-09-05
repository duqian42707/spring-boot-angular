package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.dao.SysRoleMapper;
import com.dqv5.soccer.entity.SysRole;
import com.dqv5.soccer.entity.SysRoleModule;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 * @date 2018/8/20
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole findOne(Integer id) {
        return sysRoleMapper.findOne(id);
    }

    @Override
    public SysRole save(SysRole sysRole) {
        if (sysRole.getId() == null) {
            sysRoleMapper.insert(sysRole);
        } else {
            SysRole one = sysRoleMapper.findOne(sysRole.getId());
            if ("1".equals(one.getProtect())) {
                throw new CommonRuntimeException("保留角色不能修改！");
            }
            sysRoleMapper.update(sysRole);
        }
        if (sysRole.getModuleList() != null) {
            sysRoleMapper.deleteModules(sysRole.getId());
            for (SysRoleModule roleModule : sysRole.getModuleList()) {
                sysRoleMapper.insertRoleModule(roleModule);
            }
        }
        return sysRole;
    }

    @Override
    public void delete(Integer id) {
        SysRole one = sysRoleMapper.findOne(id);
        if ("1".equals(one.getProtect())) {
            throw new CommonRuntimeException("保留角色不能删除！");
        }
        sysRoleMapper.delete(id);
    }

    @Override
    public List<SysRole> findList() {
        return sysRoleMapper.findList();
    }
}
