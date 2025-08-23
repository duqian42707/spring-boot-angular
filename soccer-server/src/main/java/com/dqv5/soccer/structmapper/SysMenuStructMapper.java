package com.dqv5.soccer.structmapper;

import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.table.SysMenuTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author duqian
 * @date 2025/8/23
 */
@Mapper
public interface SysMenuStructMapper {
    SysMenuStructMapper INSTANCE = Mappers.getMapper(SysMenuStructMapper.class);

    SysMenu entityToPojo(SysMenuTable entity);

    SysMenuTable pojoToEntity(SysMenu pojo);

    default Boolean map(Integer value) {
        if (value == null) {
            return null;
        }
        return value == 1;
    }

    default Integer map(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? 1 : 0;
    }
}
