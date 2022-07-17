package com.dqv5.soccer.pojo;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/17
 */
public  class TreeNode {
    private Integer id;
    private String name;
    private List<TreeNode> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
