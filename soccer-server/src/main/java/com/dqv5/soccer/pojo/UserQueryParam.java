package com.dqv5.soccer.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author duq
 * @date 2024/3/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryParam extends PageParam{
    private String deptId;
}
