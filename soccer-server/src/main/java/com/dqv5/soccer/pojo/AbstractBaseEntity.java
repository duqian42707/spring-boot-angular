package com.dqv5.soccer.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author duq
 * @date 2020/4/4
 */
@Data
public abstract class AbstractBaseEntity {
    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;
}
