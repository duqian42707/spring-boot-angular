package com.dqv5.soccer.service;

import com.dqv5.soccer.entity.SysDict;

import java.util.List;

/**
 * @author admin
 * @date 2018/9/10
 */
public interface SysDictService {

    void save(SysDict sysDict);

    void deleteByCode(String code);

    List<SysDict> findList();

    String findDisplay(String value, String code);


}
