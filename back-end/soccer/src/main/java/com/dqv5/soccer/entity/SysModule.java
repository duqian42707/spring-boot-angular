package com.dqv5.soccer.entity;

import java.util.Date;

/**
 * 模块
 *
 * @author duq
 * @date 2018/8/18
 */
public class SysModule {
    private Integer id;
    /**
     * 类型 ：0子模块  1根模块
     */
    private String type;
    private Integer parentId;
    private String name;
    private Date createTime;
    private Date lastModifiedTime;
    private BasicUser createUser;
    private BasicUser modUser;
}
