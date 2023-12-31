package com.dqv5.soccer.utils;

import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.table.SysMenuTable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author duq
 * @date 2022/7/19
 */
public class MenuTreeUtils {
    public static List<SysMenu> buildTree(List<SysMenuTable> allMenus) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return getChildren(allMenus, null);
    }

    public static List<SysMenu> getChildren(List<SysMenuTable> allMenus, String parentId) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return allMenus.stream()
                .filter(menu -> StringUtils.isBlank(parentId) ? StringUtils.isBlank(menu.getParentId()) : Objects.equals(parentId, menu.getParentId()))
                .map(SysMenu::of)
                .peek(sysMenu -> {
                    sysMenu.setChildren(getChildren(allMenus, sysMenu.getMenuId()));
                })
                .collect(Collectors.toList());
    }

}
