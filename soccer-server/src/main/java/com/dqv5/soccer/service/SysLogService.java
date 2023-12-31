package com.dqv5.soccer.service;

import com.dqv5.soccer.common.Pageable;
import com.dqv5.soccer.table.SysLogTable;
import com.github.pagehelper.PageInfo;

/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysLogService {
    PageInfo<SysLogTable> queryListForPage(Pageable pageable);

    void insert(SysLogTable param);
}
