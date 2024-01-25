package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.ChangePasswordParam;
import com.dqv5.soccer.pojo.UpdateProfileParam;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface SysProfileService {

    void updateProfile(UpdateProfileParam param);

    void changePassword(ChangePasswordParam param);
}
