package com.dqv5.soccer.pojo;

import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.common.TreeNodeType;
import com.dqv5.soccer.table.AbstractBaseTable;
import com.dqv5.soccer.table.SysAuthFolderTable;
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
@ApiModel("系统权限目录")
public class SysAuthFolder extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限目录id")
    private String authFolderId;

    @ApiModelProperty("权限目录名称")
    private String authFolderName;

    @ApiModelProperty("排序")
    private Integer displayIndex;

    @ApiModelProperty("分组下的权限")
    private List<SysAuth> auths;


    public static SysAuthFolder of(SysAuthFolderTable table) {
        SysAuthFolder sysAuth = new SysAuthFolder();
        BeanUtils.copyProperties(table, sysAuth);
        return sysAuth;
    }

    public SysAuthFolderTable toTable() {
        SysAuthFolderTable table = new SysAuthFolderTable();
        BeanUtils.copyProperties(this, table);
        return table;
    }

    public TreeNode toTreeNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setType(TreeNodeType.auth_folder);
        treeNode.setOrigin(this);
        treeNode.setKey(authFolderId);
        treeNode.setTitle(authFolderName);
        List<TreeNode> children = Optional.ofNullable(auths).orElse(new ArrayList<>())
                .stream().map(SysAuth::toTreeNode).collect(Collectors.toList());
        treeNode.setChildren(children);
        treeNode.setIsLeaf(children.isEmpty());
        return treeNode;
    }

}
