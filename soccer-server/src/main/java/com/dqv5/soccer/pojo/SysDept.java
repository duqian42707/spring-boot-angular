package com.dqv5.soccer.pojo;

import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.common.TreeNodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "部门")
public class SysDept extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @SchemaProperty(name = "部门id")
    private String deptId;

    @SchemaProperty(name = "部门名称")
    private String deptName;

    @SchemaProperty(name = "部门编码")
    private String deptCode;

    @SchemaProperty(name = "上级部门id")
    private String parentId;

    @SchemaProperty(name = "下级部门")
    private List<SysDept> children = new ArrayList<>();


    public TreeNode toTreeNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setType(TreeNodeType.dept);
        treeNode.setOrigin(this);
        treeNode.setKey(deptId);
        treeNode.setTitle(deptName);
        List<TreeNode> children = Optional.ofNullable(this.children).orElse(new ArrayList<>())
                .stream().map(SysDept::toTreeNode).collect(Collectors.toList());
        treeNode.setChildren(children);
        treeNode.setIsLeaf(children.isEmpty());
        return treeNode;
    }

}
