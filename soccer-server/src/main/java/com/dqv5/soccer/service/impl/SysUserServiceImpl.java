package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysDeptMapper;
import com.dqv5.soccer.mapper.SysUserDeptMapper;
import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.pojo.UserQueryParam;
import com.dqv5.soccer.table.SysUserDeptTable;
import com.dqv5.soccer.table.SysUserTable;
import com.dqv5.soccer.mapper.SysUserMapper;
import com.dqv5.soccer.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duq
 * @date 2022/7/10
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysUserDeptMapper sysUserDeptMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<SysUser> queryListForPage(UserQueryParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<SysUser> list = sysUserMapper.queryList(param);
        for (SysUser sysUser : list) {
            List<SysDept> depts = sysDeptMapper.queryByUserId(sysUser.getUserId());
            sysUser.setDepts(depts);
        }
        return new PageInfo<>(list);
    }

    @Override
    public SysUser findOne(String id) {
        SysUserTable table = sysUserMapper.selectById(id);
        SysUser sysUser = SysUser.of(table);
        List<SysDept> depts = sysDeptMapper.queryByUserId(sysUser.getUserId());
        sysUser.setDepts(depts);
        return sysUser;
    }

    @Override
    @Transactional
    public void insert(SysUser sysUser) {
        boolean exist = sysUserMapper.exists(Wrappers.query(SysUserTable.class).eq("account", sysUser.getAccount()));
        if (exist) {
            throw new CommonRuntimeException("账号已存在：" + sysUser.getAccount());
        }
        SysUserTable table = sysUser.toTable();
        table.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserMapper.insert(table);
        sysUser.getDepts().forEach(dept -> {
            SysUserDeptTable sysUserDeptTable = new SysUserDeptTable();
            sysUserDeptTable.setUserId(table.getUserId());
            sysUserDeptTable.setDeptId(dept.getDeptId());
            sysUserDeptMapper.insert(sysUserDeptTable);
        });
    }

    @Override
    @Transactional
    public void update(SysUser sysUser) {
        String userId = sysUser.getUserId();
        SysUserTable userInDb = sysUserMapper.selectById(userId);
        // todo 属性拷贝
        userInDb.setNickName(sysUser.getNickName());
        userInDb.setAvatarUrl(sysUser.getAvatarUrl());
        userInDb.setGender(sysUser.getGender());
        userInDb.setPhone(sysUser.getPhone());
        userInDb.setEmail(sysUser.getEmail());
        sysUserMapper.updateById(userInDb);

        //  部门变更
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        sysUserDeptMapper.deleteByMap(map);

        sysUser.getDepts().forEach(dept -> {
            SysUserDeptTable sysUserDeptTable = new SysUserDeptTable();
            sysUserDeptTable.setUserId(userId);
            sysUserDeptTable.setDeptId(dept.getDeptId());
            sysUserDeptMapper.insert(sysUserDeptTable);
        });

    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);
        sysUserDeptMapper.deleteByMap(map);
        sysUserMapper.deleteById(id);
    }
}
