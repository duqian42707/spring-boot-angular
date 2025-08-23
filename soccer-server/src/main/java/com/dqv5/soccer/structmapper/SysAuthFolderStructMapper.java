package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysAuthFolder;
import com.dqv5.soccer.table.SysAuthFolderTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysAuthFolderStructMapper {
    SysAuthFolderStructMapper INSTANCE = Mappers.getMapper(SysAuthFolderStructMapper.class);


    SysAuthFolder entityToPojo(SysAuthFolderTable entity);

    SysAuthFolderTable pojoToEntity(SysAuthFolder pojo);

}
