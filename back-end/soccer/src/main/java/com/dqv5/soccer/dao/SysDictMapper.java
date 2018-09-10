package com.dqv5.soccer.dao;

import com.dqv5.soccer.entity.SysDict;

import java.util.List;

/**
 * @author admin
 * @date 2018/9/10
 */
public interface SysDictMapper {
    void insert(SysDict sysDict);

    void update(SysDict sysDict);

    void delete(int id);

    void deleteByCode(String code);

    List<SysDict> findList();

}
