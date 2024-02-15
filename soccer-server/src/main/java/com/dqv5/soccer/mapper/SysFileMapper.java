package com.dqv5.soccer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.table.SysFileTable;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysFileMapper extends BaseMapper<SysFileTable> {
    List<SysFileTable> queryList();
}
