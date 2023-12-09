package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.Pageable;
import com.dqv5.soccer.table.SysUserTable;
import com.github.pagehelper.PageInfo;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface SysUserService  {
    PageInfo<SysUserTable> queryListForPage(Pageable pageable);

    SysUserTable findOne(String id);

    void insert(SysUserTable sysUserTable);

    void update(SysUserTable sysUserTable);

    void deleteById(String id);
}
