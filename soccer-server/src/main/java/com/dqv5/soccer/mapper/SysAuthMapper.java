package com.dqv5.soccer.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.table.SysAuthTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/18
 */
public interface SysAuthMapper extends BaseMapper<SysAuthTable> {

    List<SysAuthTable> queryByRoleId(String roleId);

    List<SysAuthTable> queryByUserId(String userId);

    List<SysAuth> queryList(@Param("param") JSONObject param);
}
