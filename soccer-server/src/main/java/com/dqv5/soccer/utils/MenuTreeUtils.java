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
        List<SysMenuTable> rootMenus = getChildren(allMenus, null);
        return rootMenus.stream().map(i -> {
            SysMenu sysMenu = SysMenu.of(i);
            List<SysMenuTable> childrenTable = getChildren(allMenus, sysMenu.getMenuId());
            List<SysMenu> children = childrenTable.stream().map(SysMenu::of).collect(Collectors.toList());
            List<SysAuthTable> authTables = getAuths(allAuths, sysMenu.getMenuId());
            List<SysAuth> auths = authTables.stream().map(SysAuth::of).collect(Collectors.toList());
            sysMenu.setChildren(children);
            sysMenu.setAuths(auths);
            return sysMenu;
        }).collect(Collectors.toList());
    }

    public static List<SysMenuTable> getChildren(List<SysMenuTable> allMenus, String parentId) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return allMenus.stream().filter(menu -> {
            if (StringUtils.isBlank(parentId)) {
                return StringUtils.isBlank(menu.getParentId());
            } else {
                return Objects.equals(parentId, menu.getParentId());
            }
        }).collect(Collectors.toList());
    }

    public static List<SysAuthTable> getAuths(List<SysAuthTable> allAuths, String menuId) {
        if (allAuths == null || allAuths.isEmpty() || StringUtils.isBlank(menuId)) {
            return new ArrayList<>();
        }
        return allAuths.stream().filter(auth -> Objects.equals(menuId, auth.getMenuId())).collect(Collectors.toList());
    }

}
