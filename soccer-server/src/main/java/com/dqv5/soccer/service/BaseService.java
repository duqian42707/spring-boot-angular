package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.PageInfo;
import org.springframework.data.domain.Pageable;

public interface BaseService<T> {
    PageInfo<T> findAll(Pageable pageable);

    T findOne(String id);

    void insert(T sysUser);

    void update(T sysUser);

    void deleteById(String id);
}
