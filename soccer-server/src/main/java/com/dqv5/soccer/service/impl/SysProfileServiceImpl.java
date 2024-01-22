package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.mapper.SysUserMapper;
import com.dqv5.soccer.pojo.UpdateProfileParam;
import com.dqv5.soccer.service.SysProfileService;
import com.dqv5.soccer.table.SysUserTable;
import com.dqv5.soccer.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duqian
 * @date 2024/1/22
 */
@Service
public class SysProfileServiceImpl implements SysProfileService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public void updateProfile(UpdateProfileParam param) {
        String userId = SecurityUtils.getCurrentUserId();
        SysUserTable sysUser = new SysUserTable();
        sysUser.setUserId(userId);
        sysUser.setNickName(param.getNickName());
        sysUser.setAvatarUrl(param.getAvatarUrl());
        sysUser.setGender(param.getGender());
        sysUserMapper.updateById(sysUser);
    }
}
