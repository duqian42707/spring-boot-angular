package com.dqv5.soccer.pojo;

import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.common.TreeNodeType;
import com.dqv5.soccer.table.SysDeptTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

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
@ApiModel("部门")
public class SysDept extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门id")
    private String deptId;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("部门编码")
    private String deptCode;

    @ApiModelProperty("上级部门id")
    private String parentId;

    @ApiModelProperty("下级部门")
    private List<SysDept> children = new ArrayList<>();

    public static SysDept of(SysDeptTable table) {
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(table, sysDept);
        return sysDept;
    }

    public SysDeptTable toTable() {
        SysDeptTable table = new SysDeptTable();
        BeanUtils.copyProperties(this, table);
        return table;
    }

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
