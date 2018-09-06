package com.dqv5.soccer.dao;

import com.dqv5.soccer.entity.SysFuncRight;

import java.util.List;

/**
 * @author admin
 * @date 2018/9/6
 */
public interface SysFuncRightMapper {
    List<SysFuncRight> findByModule(int moduleId);

    void insert(SysFuncRight sysFuncRight);

    void deleteByModule(int moduleId);
}
