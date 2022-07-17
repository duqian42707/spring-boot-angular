package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.pojo.entity.BaseUser;
import com.dqv5.soccer.repository.BaseUserRepository;
import com.dqv5.soccer.service.BaseUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duq
 * @date 2022/7/10
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Resource
    private BaseUserRepository baseUserRepository;

    @Override
    public Page<BaseUser> findAll(Pageable pageable) {
        return baseUserRepository.findAll(pageable);
    }
}
