package com.dqv5.soccer.pojo;

import lombok.Data;

/**
 * @author duq
 * @date 2022/7/5
 */
@Data
public class ChangePasswordParam {
    private String oldPassword;
    private String newPassword;
}
