package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.table.SysUser;
import com.dqv5.soccer.management.mapper.SysUserMapper;
import com.dqv5.soccer.management.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/10
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<SysUser> queryListForPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysUser> list = sysUserMapper.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public SysUser findOne(String id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public void insert(SysUser sysUser) {
        Optional<SysUser> opt = sysUserMapper.findByAccount(sysUser.getAccount());
        if (opt.isPresent()) {
            throw new CommonRuntimeException("账号已存在：" + sysUser.getAccount());
        }
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void update(SysUser sysUser) {
        SysUser userInDb = sysUserMapper.selectById(sysUser.getUserId());
        // todo 属性拷贝
        userInDb.setNickName(sysUser.getNickName());
        sysUserMapper.updateById(userInDb);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysUserMapper.deleteById(id);
    }
}
