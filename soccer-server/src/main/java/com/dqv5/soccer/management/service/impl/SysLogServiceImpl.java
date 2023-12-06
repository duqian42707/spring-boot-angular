package com.dqv5.soccer.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.table.SysLog;
import com.dqv5.soccer.management.mapper.SysLogMapper;
import com.dqv5.soccer.management.service.SysLogService;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;


    @Override
    public PageInfo<SysLog> queryListForPage(Pageable pageable) {
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysLog> list = sysLogMapper.selectList(queryWrapper);
        return new PageInfo<>(list);
    }

    @Override
    public SysLog findOne(String id) {
        return sysLogMapper.selectById(id);
    }

    @Override
    public void insert(SysLog param) {
        param.setLogId(null);
        AuthUser user = SecurityUtils.getCurrentUserDetail();
        if (user != null) {
            param.setUserId(user.getUserId());
            param.setUsername(user.getUsername());
            param.setNickName(user.getNickName());
        }
        sysLogMapper.insert(param);
    }

    @Override
    public void update(SysLog param) {
        throw new CommonRuntimeException("系统日志不支持修改");
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysLogMapper.deleteById(id);
    }

}
