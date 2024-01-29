package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.mapper.SysDeptMapper;
import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.service.SysDeptService;
import com.dqv5.soccer.table.SysDeptTable;
import com.dqv5.soccer.utils.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
        QueryWrapper<SysDeptTable> queryWrapper = Wrappers.query(SysDeptTable.class).orderByAsc("display_index");
        List<SysDeptTable> allDepts = sysDeptMapper.selectList(queryWrapper);
        return TreeUtils.buildDeptTree(allDepts);
    }

    @Override
    public List<TreeNode> queryAllTree() {
        QueryWrapper<SysDeptTable> queryWrapper = Wrappers.query(SysDeptTable.class).orderByAsc("display_index");
        List<SysDeptTable> allDepts = sysDeptMapper.selectList(queryWrapper);
        return TreeUtils.buildDeptTree(allDepts).stream().map(SysDept::toTreeNode).collect(Collectors.toList());
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
