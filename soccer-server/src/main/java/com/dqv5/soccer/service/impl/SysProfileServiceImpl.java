package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysUserMapper;
import com.dqv5.soccer.pojo.ChangePasswordParam;
import com.dqv5.soccer.pojo.UpdateProfileParam;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.service.SysProfileService;
import com.dqv5.soccer.table.SysUserTable;
import com.dqv5.soccer.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author duqian
 * @date 2024/1/22
 */
@Service
public class SysProfileServiceImpl implements SysProfileService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;


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

    @Override
    public void changePassword(ChangePasswordParam param) {
        String oldPassword = param.getOldPassword();
        String newPassword = param.getNewPassword();
        AuthUser authUser = SecurityUtils.getCurrentUserDetail();
        if (authUser == null) {
            throw new CommonRuntimeException("当前用户信息异常");
        }
        if (!passwordEncoder.matches(oldPassword, authUser.getPassword())) {
            throw new CommonRuntimeException("原密码错误！");
        }
        String encoded = passwordEncoder.encode(newPassword);
        SysUserTable sysUser = new SysUserTable();
        sysUser.setUserId(authUser.getUserId());
        sysUser.setPassword(encoded);
        sysUser.setLastPasswordResetTime(new Date());
        sysUserMapper.updateById(sysUser);
    }
}
