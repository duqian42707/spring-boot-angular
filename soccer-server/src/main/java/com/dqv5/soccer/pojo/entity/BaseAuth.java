package com.dqv5.soccer.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "base_auth")
public class BaseAuth extends BaseEntity implements Serializable {
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
    private BaseMenu baseMenu;


}
