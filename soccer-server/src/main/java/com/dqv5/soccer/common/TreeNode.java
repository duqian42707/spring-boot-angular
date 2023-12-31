package com.dqv5.soccer.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duq
 * @date 2022/7/17
 */
@Data
public class TreeNode {
    private String key;
    private String title;
    private Boolean isLeaf;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNodeType type;
    private Object origin;


}
