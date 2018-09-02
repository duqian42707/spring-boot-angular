package com.dqv5.soccer.service;

import com.dqv5.soccer.entity.BasicUser;

import java.util.List;

/**
 * @author admin
 * @date 2018/7/7
 */
public interface BasicUserService {
    List<BasicUser> findAll();

    BasicUser findOne(Integer id);

    BasicUser save(BasicUser basicUser);

    void delete(Integer id);

}
