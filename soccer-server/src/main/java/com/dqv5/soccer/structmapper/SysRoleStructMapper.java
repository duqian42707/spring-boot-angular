package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysRole;
import com.dqv5.soccer.table.SysRoleTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysRoleStructMapper {
    SysRoleStructMapper INSTANCE = Mappers.getMapper(SysRoleStructMapper.class);


    SysRole entityToPojo(SysRoleTable entity);

    SysRoleTable pojoToEntity(SysRole pojo);
}
