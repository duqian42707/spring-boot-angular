package com.dqv5.soccer.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.management.table.SysUser;

import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/5
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    Optional<SysUser> findByAccount(String account);
}
