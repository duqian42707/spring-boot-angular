package com.dqv5.soccer.management.entity;

import com.dqv5.soccer.pojo.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author admin
 * @date 2022/7/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
@Entity
@Table(name = "sys_log")
public class SysLog extends AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String detail;
    private String ip;

}
