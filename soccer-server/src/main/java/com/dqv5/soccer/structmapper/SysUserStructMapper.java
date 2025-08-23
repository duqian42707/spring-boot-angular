package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.table.SysUserTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysUserStructMapper {
    SysUserStructMapper INSTANCE = Mappers.getMapper(SysUserStructMapper.class);


    SysUser entityToPojo(SysUserTable entity);

    SysUserTable pojoToEntity(SysUser pojo);
}
