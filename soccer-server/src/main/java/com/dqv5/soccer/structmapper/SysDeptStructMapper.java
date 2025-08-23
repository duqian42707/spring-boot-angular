package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.table.SysDeptTable;
import com.dqv5.soccer.table.SysUserTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysDeptStructMapper {
    SysDeptStructMapper INSTANCE = Mappers.getMapper(SysDeptStructMapper.class);


    SysDept entityToPojo(SysDeptTable entity);

    SysDeptTable pojoToEntity(SysDept pojo);
}
