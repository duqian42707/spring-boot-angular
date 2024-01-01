package com.dqv5.soccer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.table.SysUserTable;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysUserMapper extends BaseMapper<SysUserTable> {
    List<SysUser> queryList();
}
