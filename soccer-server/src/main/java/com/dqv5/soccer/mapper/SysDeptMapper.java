package com.dqv5.soccer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.table.SysDeptTable;

import java.util.List;

/**
 * @author duq
 * @date 2024/1/10
 */
public interface SysDeptMapper extends BaseMapper<SysDeptTable> {
    List<SysDept> queryByUserId(String userId);
}
