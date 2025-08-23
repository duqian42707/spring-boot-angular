package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.table.SysAuthTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysAuthStructMapper {
    SysAuthStructMapper INSTANCE = Mappers.getMapper(SysAuthStructMapper.class);


    SysAuth entityToPojo(SysAuthTable entity);

    SysAuthTable pojoToEntity(SysAuth pojo);

}
