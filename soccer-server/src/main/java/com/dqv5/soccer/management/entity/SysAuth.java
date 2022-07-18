package com.dqv5.soccer.management.entity;

import com.dqv5.soccer.pojo.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_auth")
public class SysAuth extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String authId;

    private String authValue;

    private String authName;

    private Integer displayIndex;
    /**
     * 所属菜单
     */
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private SysMenu sysMenu;


}
