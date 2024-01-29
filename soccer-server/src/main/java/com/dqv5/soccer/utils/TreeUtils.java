package com.dqv5.soccer.utils;

import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.table.SysDeptTable;
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
public class TreeUtils {
    public static List<SysMenu> buildMenuTree(List<SysMenuTable> allMenus) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return getMenuChildren(allMenus, null);
    }

    public static List<SysDept> buildDeptTree(List<SysDeptTable> allDepts) {
        if (allDepts == null || allDepts.isEmpty()) {
            return new ArrayList<>();
        }
        return getDeptChildren(allDepts, null);
    }

    private static List<SysMenu> getMenuChildren(List<SysMenuTable> allMenus, String parentId) {
        if (allMenus == null || allMenus.isEmpty()) {
            return new ArrayList<>();
        }
        return allMenus.stream()
                .filter(menu -> StringUtils.isBlank(parentId) ? StringUtils.isBlank(menu.getParentId()) : Objects.equals(parentId, menu.getParentId()))
                .map(SysMenu::of)
                .peek(sysMenu -> {
                    sysMenu.setChildren(getMenuChildren(allMenus, sysMenu.getMenuId()));
                })
                .collect(Collectors.toList());
    }

    private static List<SysDept> getDeptChildren(List<SysDeptTable> allDepts, String parentId) {
        if (allDepts == null || allDepts.isEmpty()) {
            return new ArrayList<>();
        }
        return allDepts.stream()
                .filter(dept -> StringUtils.isBlank(parentId) ? StringUtils.isBlank(dept.getParentId()) : Objects.equals(parentId, dept.getParentId()))
                .map(SysDept::of)
                .peek(sysDept -> {
                    sysDept.setChildren(getDeptChildren(allDepts, sysDept.getDeptId()));
                })
                .collect(Collectors.toList());
    }

}
