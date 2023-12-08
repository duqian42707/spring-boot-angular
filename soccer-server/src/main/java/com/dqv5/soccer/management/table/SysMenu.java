package com.dqv5.soccer.management.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dqv5.soccer.pojo.AbstractBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
     * 父级菜单id
     */
    private String parentId;

}
