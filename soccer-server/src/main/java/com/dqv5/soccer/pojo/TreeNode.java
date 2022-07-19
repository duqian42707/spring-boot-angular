package com.dqv5.soccer.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/17
 */
@Data
public class TreeNode {
    private String key;
    private String name;
    private List<TreeNode> children;
}
