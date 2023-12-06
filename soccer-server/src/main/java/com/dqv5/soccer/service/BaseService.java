package com.dqv5.soccer.service;

import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;

public interface BaseService<T> {
    PageInfo<T> queryListForPage(Pageable pageable);

    T findOne(String id);

    void insert(T sysUser);

    void update(T sysUser);

    void deleteById(String id);
}
