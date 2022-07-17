package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.pojo.entity.BaseRole;
import com.dqv5.soccer.repository.BaseRoleRepository;
import com.dqv5.soccer.service.BaseRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duq
 * @date 2022/7/10
 */
@Service
public class BaseRoleServiceImpl implements BaseRoleService {
    @Resource
    private BaseRoleRepository baseRoleRepository;

    @Override
    public Page<BaseRole> findAll(Pageable pageable) {
        return baseRoleRepository.findAll(pageable);
    }
}
