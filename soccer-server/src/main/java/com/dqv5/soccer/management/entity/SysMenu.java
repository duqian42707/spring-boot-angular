package com.dqv5.soccer.management.entity;

import com.dqv5.soccer.pojo.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_menu")
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
     * 唯一标识
     */
    @Column(name = "SYSTEM_CODE", unique = true)
    private String systemCode;

    /**
     * 父级菜单
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = SysMenu.class)
    @JoinColumn(name = "parent_id")
    private SysMenu parentMenu;

    /**
     * 下级菜单
     */
    @OneToMany(mappedBy = "parentMenu", fetch = FetchType.LAZY)
    private Set<SysMenu> children;


}
