package com.dqv5.soccer.repository;

import com.dqv5.soccer.entity.BasicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @date 2018/7/7
 */
@Repository
public interface BasicUserRepository extends JpaRepository<BasicUser, Integer> {

    List<BasicUser> findAllByOrderById();

    BasicUser findByAccount(String account);

}
