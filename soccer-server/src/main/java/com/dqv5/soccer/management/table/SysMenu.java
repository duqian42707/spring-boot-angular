package com.dqv5.soccer.management.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dqv5.soccer.pojo.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@TableName("sys_menu")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysMenu extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_UUID)
    private String menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 唯一标识
     */
    private String menuCode;

    /**
     * 路由链接
     */
    private String link;
    /**
     * 外部链接
     */
    private String externalLink;
    /**
     * 介绍
     */
    private String intro;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否隐藏菜单
     */
    private Integer hide;
    /**
     * 是否隐藏菜单
     */
    private Integer hideChildren;
    /**
     * 显示顺序
     */
    private Integer displayIndex;

    /**
     * 父级菜单
     */
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SysMenu.class)
//    @JoinColumn(name = "parent_id")
    @JsonIgnore // 避免死循环
    @TableField(exist = false)
    private SysMenu parentMenu;

    /**
     * 下级菜单
     */
//    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
    @TableField(exist = false)
    private Set<SysMenu> children;

    /**
     * 重写equals、hashCode、toString 方法，不要加父级菜单和下级菜单，可避免jpa无限循环查询
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysMenu sysMenu = (SysMenu) o;
        return Objects.equals(hide, sysMenu.hide) && Objects.equals(hideChildren, sysMenu.hideChildren) && Objects.equals(menuId, sysMenu.menuId) && Objects.equals(menuName, sysMenu.menuName) && Objects.equals(menuCode, sysMenu.menuCode) && Objects.equals(link, sysMenu.link) && Objects.equals(externalLink, sysMenu.externalLink) && Objects.equals(intro, sysMenu.intro) && Objects.equals(icon, sysMenu.icon) && Objects.equals(displayIndex, sysMenu.displayIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), menuId, menuName, menuCode, link, externalLink, intro, icon, hide, hideChildren, displayIndex);
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", link='" + link + '\'' +
                ", externalLink='" + externalLink + '\'' +
                ", intro='" + intro + '\'' +
                ", icon='" + icon + '\'' +
                ", hide=" + hide +
                ", hideChildren=" + hideChildren +
                ", displayIndex=" + displayIndex +
                '}';
    }
}
