package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.dao.BasicUserMapper;
import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.service.BasicUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

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
    private BasicUserMapper basicUserMapper;

    @Override
    public List<BasicUser> findAll() {
        return basicUserMapper.findAllByOrderById();
    }

    @Override
    public BasicUser findOne(Integer id) {
        return basicUserMapper.findOne(id);
    }

    @Override
    public BasicUser save(BasicUser basicUser) {
        return basicUserMapper.save(basicUser);
    }


    @Override
    public void delete(Integer id) {
        BasicUser one = new BasicUser();
        basicUserMapper.delete(id);
    }


}
