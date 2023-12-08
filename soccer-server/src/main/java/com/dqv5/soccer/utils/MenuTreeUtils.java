package com.dqv5.soccer.utils;

import com.dqv5.soccer.management.table.SysMenu;
import com.dqv5.soccer.pojo.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author duq
 * @date 2022/7/19
 */
public class MenuTreeUtils {
    public static List<TreeNode> buildTree(Collection<SysMenu> menus) {
        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }
        return menus.stream().map(i -> {
            TreeNode treeNode = new TreeNode();
            treeNode.setKey(i.getMenuId());
            treeNode.setName(i.getMenuName());
            // todo setChildren
//            treeNode.setChildren(buildTree(i.getChildren()));
            return treeNode;
        }).collect(Collectors.toList());
    }

}
