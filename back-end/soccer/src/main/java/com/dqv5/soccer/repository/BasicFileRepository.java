package com.dqv5.soccer.repository;

import com.dqv5.soccer.entity.BasicFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author duq
 * @date 2018/8/4
 */
@Repository
public interface BasicFileRepository extends JpaRepository<BasicFile, Integer> {

}
