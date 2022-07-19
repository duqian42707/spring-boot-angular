package com.dqv5.soccer.management.service;

import com.dqv5.soccer.management.entity.SysAuth;
import com.dqv5.soccer.service.BaseService;

import java.util.List;

/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysAuthService extends BaseService<SysAuth> {
    List<SysAuth> findAll(String menuId);
}
