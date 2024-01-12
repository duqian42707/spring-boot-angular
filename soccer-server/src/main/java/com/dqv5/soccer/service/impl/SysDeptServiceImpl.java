package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.common.Pageable;
import com.dqv5.soccer.mapper.SysDeptMapper;
import com.dqv5.soccer.service.SysDeptService;
import com.dqv5.soccer.table.SysDeptTable;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author duq
 * @date 2024/1/12
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public PageInfo<SysDeptTable> queryListForPage(Pageable pageable) {
        return null;
    }

    @Override
    public void insert(SysDeptTable param) {

    }
}
