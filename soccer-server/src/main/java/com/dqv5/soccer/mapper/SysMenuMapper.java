package com.dqv5.soccer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.table.SysMenuTable;
import com.dqv5.soccer.table.SysRoleTable;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysMenuMapper extends BaseMapper<SysMenuTable> {

    List<SysMenuTable> queryByRoleId(String roleId);
}
