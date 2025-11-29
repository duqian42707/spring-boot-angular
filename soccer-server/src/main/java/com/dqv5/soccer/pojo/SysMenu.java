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
@Schema(name = "系统菜单")
public class SysMenu extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @SchemaProperty(name = "菜单id")
    private String menuId;

    @SchemaProperty(name = "菜单名称")
    private String menuName;

    @SchemaProperty(name = "唯一标识")
    private String menuCode;

    @SchemaProperty(name = "路由链接")
    private String link;

    @SchemaProperty(name = "外部链接")
    private String externalLink;

    @SchemaProperty(name = "介绍")
    private String intro;

    @SchemaProperty(name = "图标")
    private String icon;

    @SchemaProperty(name = "是否隐藏菜单")
    private Boolean hide;

    @SchemaProperty(name = "是否隐藏菜单")
    private Boolean hideChildren;

    @SchemaProperty(name = "显示顺序")
    private Integer displayIndex;

    @SchemaProperty(name = "父级菜单id")
    private String parentId;

    @SchemaProperty(name = "下级菜单")
    private List<SysMenu> children = new ArrayList<>();


    public TreeNode toTreeNode() {
        TreeNode treeNode = new TreeNode();
        treeNode.setType(TreeNodeType.menu);
        treeNode.setOrigin(this);
        treeNode.setKey(menuId);
        treeNode.setTitle(menuName);
        List<TreeNode> children = Optional.ofNullable(this.children).orElse(new ArrayList<>())
                .stream().map(SysMenu::toTreeNode).collect(Collectors.toList());
        treeNode.setChildren(children);
        treeNode.setIsLeaf(children.isEmpty());
        return treeNode;
    }

}
