package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.table.SysUserTable;
import com.dqv5.soccer.mapper.SysUserMapper;
import com.dqv5.soccer.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public PageInfo<SysUserTable> queryListForPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysUserTable> list = sysUserMapper.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public SysUserTable findOne(String id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public void insert(SysUserTable sysUserTable) {
        boolean exist = sysUserMapper.exists(Wrappers.query(SysUserTable.class).eq("account", sysUserTable.getAccount()));
        if (exist) {
            throw new CommonRuntimeException("账号已存在：" + sysUserTable.getAccount());
        }
        sysUserTable.setPassword(passwordEncoder.encode(sysUserTable.getPassword()));
        sysUserMapper.insert(sysUserTable);
    }

    @Override
    public void update(SysUserTable sysUserTable) {
        SysUserTable userInDb = sysUserMapper.selectById(sysUserTable.getUserId());
        // todo 属性拷贝
        userInDb.setNickName(sysUserTable.getNickName());
        sysUserMapper.updateById(userInDb);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysUserMapper.deleteById(id);
    }
}
