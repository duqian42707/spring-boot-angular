package com.dqv5.soccer.service;

import com.dqv5.soccer.entity.BasicFile;

import java.util.List;

/**
 * @author duq
 * @date 2018/8/4
 */
public interface BasicFileService {
    String createToken();

    BasicFile findOne(Integer id);

    BasicFile save(BasicFile basicFile);

    void delete(Integer id);

    List findAll();

    BasicFile generateUrl(BasicFile basicFile);

    List<BasicFile> generateUrl(List<BasicFile> files);
}
