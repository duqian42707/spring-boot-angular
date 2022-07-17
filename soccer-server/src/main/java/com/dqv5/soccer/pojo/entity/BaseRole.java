package com.dqv5.soccer.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author duq
 * @date 2022/7/12
 */

/**
 * @author duq
 * @date 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "base_role")
public class BaseRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String roleId;
    private String roleValue;
    private String roleName;
}
