package com.dqv5.soccer.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.management.table.SysRole;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> queryByUserId(String userId);
}
