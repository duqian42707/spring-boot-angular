package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.entity.BaseRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author duq
 * @date 2022/7/10
 */
public interface BaseRoleService {
    Page<BaseRole> findAll(Pageable pageable);

}
