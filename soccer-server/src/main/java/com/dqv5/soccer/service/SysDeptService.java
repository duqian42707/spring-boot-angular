package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.table.SysDeptTable;

import java.util.List;

/**
 * @author admin
 * @date 2022/7/17
 */
public interface SysDeptService {
    List<SysDept> queryAll();

    SysDept findOne(String id);

    void insert(SysDeptTable param);

    void update(SysDeptTable param);

    void deleteById(String id);
}
