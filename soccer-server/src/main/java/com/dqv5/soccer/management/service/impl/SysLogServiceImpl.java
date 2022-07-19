package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.entity.SysLog;
import com.dqv5.soccer.management.repository.SysLogRepository;
import com.dqv5.soccer.management.service.SysLogService;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.utils.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogRepository sysLogRepository;


    @Override
    public PageInfo<SysLog> queryListForPage(Pageable pageable) {
        Page<SysLog> page = sysLogRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysLog findOne(String id) {
        return sysLogRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("菜单id不存在:" + id));
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
        sysLogRepository.save(param);
    }

    @Override
    public void update(SysLog param) {
        throw new CommonRuntimeException("系统日志不支持修改");
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysLogRepository.deleteById(id);
    }

}
