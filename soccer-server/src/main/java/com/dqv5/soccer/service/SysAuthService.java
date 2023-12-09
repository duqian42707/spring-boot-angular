package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.Pageable;
import com.dqv5.soccer.table.SysAuthTable;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author admin
 * @date 2022/7/17
 */

public interface SysAuthService {
    List<SysAuthTable> findAll(String menuId);

    PageInfo<SysAuthTable> queryListForPage(Pageable pageable);

    SysAuthTable findOne(String id);

    void insert(SysAuthTable sysUser);

    void update(SysAuthTable sysUser);

    void deleteById(String id);

}
