package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysFile;
import com.dqv5.soccer.table.SysFileTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysFileStructMapper {
    SysFileStructMapper INSTANCE = Mappers.getMapper(SysFileStructMapper.class);

    @Mapping(source = "fileId", target = "url", qualifiedByName = "appendUrlPrefix")
    SysFile entityToPojo(SysFileTable entity);

    SysFileTable pojoToEntity(SysFile pojo);

    @Named("appendUrlPrefix")
    default String appendPrefix(String fileId) {
        return "/api/file/file/" + fileId;
    }
}
