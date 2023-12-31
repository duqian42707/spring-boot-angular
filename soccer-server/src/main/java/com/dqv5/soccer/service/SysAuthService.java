package com.dqv5.soccer.service;

import com.dqv5.soccer.common.Pageable;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.pojo.SysAuthFolder;
import com.dqv5.soccer.table.SysAuthTable;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author admin
 * @date 2022/7/17
 */

public interface SysAuthService {
    List<TreeNode> findAuthTree();

    PageInfo<SysAuth> queryListForPage(Pageable pageable);

    List<SysAuthFolder> findFolders();

    SysAuthTable findOne(String id);

    void insert(SysAuthTable sysUser);

    void update(SysAuthTable sysUser);

    void deleteById(String id);

}
