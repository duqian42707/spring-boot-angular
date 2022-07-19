package com.dqv5.soccer.management.entity;

import com.dqv5.soccer.pojo.AbstractBaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "sys_menu")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SysMenu extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 唯一标识
     */
    @Column(name = "MENU_CODE", unique = true)
    private String menuCode;

    /**
     * 路由链接
     */
    private String link;
    /**
     * 外部链接
     */
    @Column(name = "EXTERNAL_LINK", length = 1000)
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
    @Column(name = "hide")
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean hide;
    /**
     * 是否隐藏菜单
     */
    @Column(name = "hide_children")
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean hideChildren;
    /**
     * 显示顺序
     */
    private Integer displayIndex;

    /**
     * 父级菜单
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SysMenu.class)
    @JoinColumn(name = "parent_id")
    @JsonIgnore // 避免死循环
    private SysMenu parentMenu;

    /**
     * 下级菜单
     */
    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
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
        return hide == sysMenu.hide && hideChildren == sysMenu.hideChildren && Objects.equals(menuId, sysMenu.menuId) && Objects.equals(menuName, sysMenu.menuName) && Objects.equals(menuCode, sysMenu.menuCode) && Objects.equals(link, sysMenu.link) && Objects.equals(externalLink, sysMenu.externalLink) && Objects.equals(intro, sysMenu.intro) && Objects.equals(icon, sysMenu.icon) && Objects.equals(displayIndex, sysMenu.displayIndex);
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
