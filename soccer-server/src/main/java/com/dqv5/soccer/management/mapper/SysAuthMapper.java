package com.dqv5.soccer.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.management.table.SysAuth;
import com.dqv5.soccer.management.table.SysMenu;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysAuthMapper extends BaseMapper<SysAuth> {

    List<SysAuth> findByMenu(SysMenu menu);

    boolean existsByAuthValueAndMenu(String authValue, SysMenu menu);

    boolean existsByAuthNameAndMenu(String authName, SysMenu menu);

    boolean existsByAuthValueAndMenuAndAuthIdNot(String authValue, SysMenu menu, String authId);

    boolean existsByAuthNameAndMenuAndAuthIdNot(String authName, SysMenu menu, String authId);
}
