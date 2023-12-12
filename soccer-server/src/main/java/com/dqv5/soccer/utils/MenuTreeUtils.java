package com.dqv5.soccer.utils;

import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.table.SysAuthTable;
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
    public static List<SysMenu> buildTree(List<SysMenuTable> allMenus, List<SysAuthTable> allAuths) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return getChildren(allMenus, allAuths, null);
    }

    public static List<SysMenu> getChildren(List<SysMenuTable> allMenus, List<SysAuthTable> allAuths, String parentId) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return allMenus.stream()
                .filter(menu -> StringUtils.isBlank(parentId) ? StringUtils.isBlank(menu.getParentId()) : Objects.equals(parentId, menu.getParentId()))
                .map(SysMenu::of)
                .peek(sysMenu -> {
                    sysMenu.setAuths(getAuths(allAuths, sysMenu.getMenuId()));
                    sysMenu.setChildren(getChildren(allMenus, allAuths, sysMenu.getMenuId()));
                })
                .collect(Collectors.toList());
    }

    public static List<SysAuth> getAuths(List<SysAuthTable> allAuths, String menuId) {
        if (allAuths == null || allAuths.isEmpty() || StringUtils.isBlank(menuId)) {
            return new ArrayList<>();
        }
        return allAuths.stream()
                .filter(auth -> Objects.equals(menuId, auth.getMenuId()))
                .map(SysAuth::of)
                .collect(Collectors.toList());
    }

}
