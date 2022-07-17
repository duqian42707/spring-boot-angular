package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.pojo.entity.BaseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface BaseUserService {
    PageInfo<BaseUser> findAll(Pageable pageable);

    BaseUser findOne(String id);

    void insert(BaseUser baseUser);

    void update(BaseUser baseUser);

    void deleteById(String id);
}
