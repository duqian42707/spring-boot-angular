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
@Schema(name = "系统权限目录")
public class SysAuthFolder extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @SchemaProperty(name = "权限目录id")
    private String authFolderId;

    @SchemaProperty(name = "权限目录名称")
    private String authFolderName;

    @SchemaProperty(name = "排序")
    private Integer displayIndex;

    @SchemaProperty(name = "分组下的权限")
    private List<SysAuth> auths;


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
