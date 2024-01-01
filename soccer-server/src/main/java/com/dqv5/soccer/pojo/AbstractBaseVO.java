package com.dqv5.soccer.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author duq
 * @date 2024/1/1
 */
@Data
public abstract class AbstractBaseVO {

    private String createdBy;

    private String createdNickName;

    private Date createdDate;

    private String lastModifiedBy;

    private String lastModifiedNickName;

    private Date lastModifiedDate;

}
