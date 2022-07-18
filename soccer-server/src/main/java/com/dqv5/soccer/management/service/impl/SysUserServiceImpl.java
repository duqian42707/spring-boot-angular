package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.management.entity.SysUser;
import com.dqv5.soccer.management.repository.SysUserRepository;
import com.dqv5.soccer.management.service.SysUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/10
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<SysUser> findAll(Pageable pageable) {
        Page<SysUser> page = sysUserRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysUser findOne(String id) {
        return sysUserRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("用户id不存在:" + id));
    }

    @Override
    public void insert(SysUser sysUser) {
        Optional<SysUser> opt = sysUserRepository.findByAccount(sysUser.getAccount());
        if (opt.isPresent()) {
            throw new CommonRuntimeException("账号已存在：" + sysUser.getAccount());
        }
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserRepository.save(sysUser);
    }

    @Override
    public void update(SysUser sysUser) {
        SysUser userInDb = sysUserRepository.findById(sysUser.getUserId()).orElseThrow(() -> new CommonRuntimeException("用户id不存在:" + sysUser.getUserId()));
        // todo 属性拷贝
        userInDb.setNickName(sysUser.getNickName());
        sysUserRepository.save(userInDb);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysUserRepository.deleteById(id);
    }
}
