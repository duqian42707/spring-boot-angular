package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.repository.BasicUserRepository;
import com.dqv5.soccer.service.BasicUserService;
import com.dqv5.soccer.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2018/7/9
 */
@Service
public class BasicUserServiceImpl implements BasicUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BasicUserRepository basicUserRepository;

    @Override
    public List<BasicUser> findAll() {
        return basicUserRepository.findAllByOrderById();
    }

    @Override
    public BasicUser findOne(Integer id) {
        return basicUserRepository.findOne(id);
    }

    @Override
    public BasicUser insert(BasicUser basicUser) {
        return basicUserRepository.save(basicUser);
    }

    @Override
    public BasicUser update(BasicUser basicUser) {
        return basicUserRepository.save(basicUser);
    }

    @Override
    public void delete(Integer id) {
        BasicUser one = new BasicUser();
        basicUserRepository.delete(id);
    }


}
