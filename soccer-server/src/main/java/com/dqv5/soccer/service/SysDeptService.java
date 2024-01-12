package com.dqv5.soccer.service;

import com.dqv5.soccer.common.Pageable;
import com.dqv5.soccer.table.SysDeptTable;
import com.github.pagehelper.PageInfo;

/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysDeptService {
    PageInfo<SysDeptTable> queryListForPage(Pageable pageable);

    void insert(SysDeptTable param);
}
