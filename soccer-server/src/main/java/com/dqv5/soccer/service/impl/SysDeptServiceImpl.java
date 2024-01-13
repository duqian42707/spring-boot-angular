package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.mapper.SysDeptMapper;
import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.service.SysDeptService;
import com.dqv5.soccer.table.SysDeptTable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duq
 * @date 2024/1/12
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;


    @Override
    public List<SysDept> queryAll() {
        return null;
    }

    @Override
    public SysDept findOne(String id) {
        return null;
    }

    @Override
    public void insert(SysDeptTable param) {

    }

    @Override
    public void update(SysDeptTable param) {

    }

    @Override
    public void deleteById(String id) {

    }
}
