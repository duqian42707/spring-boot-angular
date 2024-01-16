package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.mapper.SysConfigMapper;
import com.dqv5.soccer.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duq
 * @date 2024/1/16
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Resource
    private SysConfigMapper sysConfigMapper;
}
