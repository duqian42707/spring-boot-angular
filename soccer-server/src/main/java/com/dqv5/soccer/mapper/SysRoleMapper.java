package com.dqv5.soccer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.table.SysRoleTable;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysRoleMapper extends BaseMapper<SysRoleTable> {

    List<SysRoleTable> queryByUserId(String userId);
}
