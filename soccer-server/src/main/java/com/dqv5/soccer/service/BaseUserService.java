package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.entity.BaseUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface BaseUserService {
    Page<BaseUser> findAll(Pageable pageable);
}
