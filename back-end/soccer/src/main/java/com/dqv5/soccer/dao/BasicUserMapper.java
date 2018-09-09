package com.dqv5.soccer.dao;

import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.entity.BasicUserRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author duq
 * @date 2018/9/1
 */
public interface BasicUserMapper {
    List<BasicUser> findAllByOrderById();

    BasicUser findByAccount(String account);

    BasicUser findOne(Integer id);

    void insert(BasicUser basicUser);

    void updateUserInfo(BasicUser basicUser);
    void updatePassword(BasicUser basicUser);

    void delete(Integer id);

    void insertUserRole(BasicUserRole basicUserRole);

    void deleteUserRoles(int userId);
}
