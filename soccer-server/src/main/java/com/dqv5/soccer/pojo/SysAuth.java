package com.dqv5.soccer.pojo;

import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.common.TreeNodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "系统权限")
public class SysAuth extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @SchemaProperty(name = "权限id")
    private String authId;

    @SchemaProperty(name = "权限标识")
    private String authValue;

    @SchemaProperty(name = "权限名称")
    private String authName;

    @SchemaProperty(name = "权限目录id")
    private String authFolderId;

    @SchemaProperty(name = "排序")
    private Integer displayIndex;

    @SchemaProperty(name = "权限分组名称")
    private String authFolderName;


    public TreeNode toTreeNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setType(TreeNodeType.auth);
        treeNode.setOrigin(this);
        treeNode.setKey(authId);
        treeNode.setTitle(authName);
        treeNode.setIsLeaf(true);
        return treeNode;
    }

}
