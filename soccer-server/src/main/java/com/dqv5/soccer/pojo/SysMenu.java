package com.dqv5.soccer.pojo;

import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.common.TreeNodeType;
import com.dqv5.soccer.table.AbstractBaseTable;
import com.dqv5.soccer.table.SysMenuTable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
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
@ApiModel("系统菜单")
public class SysMenu extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    private String menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("唯一标识")
    private String menuCode;

    @ApiModelProperty("路由链接")
    private String link;

    @ApiModelProperty("外部链接")
    private String externalLink;

    @ApiModelProperty("介绍")
    private String intro;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否隐藏菜单")
    private Boolean hide;

    @ApiModelProperty("是否隐藏菜单")
    private Boolean hideChildren;

    @ApiModelProperty("显示顺序")
    private Integer displayIndex;

    @ApiModelProperty("父级菜单id")
    private String parentId;

    @ApiModelProperty("下级菜单")
    private List<SysMenu> children = new ArrayList<>();

    public static SysMenu of(SysMenuTable table) {
        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(table, sysMenu);
        sysMenu.setHide(table.getHide() == null ? null : (table.getHide() == 1));
        sysMenu.setHideChildren(table.getHideChildren() == null ? null : (table.getHideChildren() == 1));
        sysMenu.setIcon(StringUtils.isNotBlank(table.getIcon()) ? table.getIcon() : null);
        return sysMenu;
    }

    public SysMenuTable toTable() {
        SysMenuTable table = new SysMenuTable();
        BeanUtils.copyProperties(this, table);
        table.setHide(hide == null ? null : (hide ? 1 : 0));
        table.setHideChildren(hideChildren == null ? null : (hideChildren ? 1 : 0));
        return table;
    }

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
