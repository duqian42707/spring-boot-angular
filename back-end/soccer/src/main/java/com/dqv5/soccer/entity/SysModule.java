package com.dqv5.soccer.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 模块
 *
 * @author duq
 * @date 2018/8/18
 */
@Entity
@Table(name = "sys_module")
public class SysModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 类型 ：0子模块  1根模块
     */
    private String type;
    private Integer parentId;
    private String name;
    private Date createTime;
    private Date lastModifiedTime;
    @ManyToOne
    private BasicUser createUser;
    @ManyToOne
    private BasicUser modUser;
}
