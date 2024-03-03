package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.pojo.UserQueryParam;
import com.github.pagehelper.PageInfo;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface SysUserService  {
    PageInfo<SysUser> queryListForPage(UserQueryParam pageable);

    SysUser findOne(String id);

    void insert(SysUser sysUser);

    void update(SysUser sysUser);

    void deleteById(String id);
}
