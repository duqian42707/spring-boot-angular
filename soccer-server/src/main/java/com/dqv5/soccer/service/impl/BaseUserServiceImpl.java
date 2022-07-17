package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.pojo.entity.BaseUser;
import com.dqv5.soccer.repository.BaseUserRepository;
import com.dqv5.soccer.service.BaseUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/10
 */
@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Resource
    private BaseUserRepository baseUserRepository;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public PageInfo<BaseUser> findAll(Pageable pageable) {
        Page<BaseUser> page = baseUserRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public BaseUser findOne(String id) {
        return baseUserRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("用户id不存在:" + id));
    }

    @Override
    public void insert(BaseUser baseUser) {
        Optional<BaseUser> opt = baseUserRepository.findByAccount(baseUser.getAccount());
        if (opt.isPresent()) {
            throw new CommonRuntimeException("账号已存在：" + baseUser.getAccount());
        }
        baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
        baseUserRepository.save(baseUser);
    }

    @Override
    public void update(BaseUser baseUser) {
        BaseUser userInDb = baseUserRepository.findById(baseUser.getUserId()).orElseThrow(() -> new CommonRuntimeException("用户id不存在:" + baseUser.getUserId()));
        // todo 属性拷贝
        userInDb.setNickName(baseUser.getNickName());
        baseUserRepository.save(userInDb);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        baseUserRepository.deleteById(id);
    }
}
