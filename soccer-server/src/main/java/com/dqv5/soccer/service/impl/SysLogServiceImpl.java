package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dqv5.soccer.table.SysLogTable;
import com.dqv5.soccer.mapper.SysLogMapper;
import com.dqv5.soccer.service.SysLogService;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.utils.SecurityUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;


    @Override
    public PageInfo<SysLogTable> queryListForPage(Pageable pageable) {
        QueryWrapper<SysLogTable> queryWrapper = new QueryWrapper<>();
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysLogTable> list = sysLogMapper.selectList(queryWrapper);
        return new PageInfo<>(list);
    }


    @Override
    public void insert(SysLogTable param) {
        param.setLogId(null);
        AuthUser user = SecurityUtils.getCurrentUserDetail();
        if (user != null) {
            param.setUserId(user.getUserId());
            param.setUsername(user.getUsername());
            param.setNickName(user.getNickName());
        }
        sysLogMapper.insert(param);
    }


}
