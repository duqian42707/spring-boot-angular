package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.exception.CommonRuntimeException;
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
        SysDeptTable table = sysDeptMapper.selectById(id);
        return SysDept.of(table);
    }

    @Override
    public void insert(SysDeptTable table) {
        table.setDeptId(null);
        String deptCode = table.getDeptCode();
        QueryWrapper<SysDeptTable> queryWrapper = Wrappers.query(SysDeptTable.class).eq("dept_code", deptCode);
        if (sysDeptMapper.exists(queryWrapper)) {
            throw new CommonRuntimeException("部门编码已存在：" + deptCode);
        }
        sysDeptMapper.insert(table);
    }

    @Override
    public void update(SysDeptTable param) {
        String deptId = param.getDeptId();
        String deptCode = param.getDeptCode();
        SysDeptTable dataInDB = sysDeptMapper.selectById(deptId);
        QueryWrapper<SysDeptTable> queryWrapper = Wrappers.query(SysDeptTable.class).eq("dept_code", deptCode).ne("dept_id", deptId);
        if (sysDeptMapper.exists(queryWrapper)) {
            throw new CommonRuntimeException("部门编码已存在：" + deptCode);
        }
        dataInDB.setDeptCode(param.getDeptCode());
        dataInDB.setDeptName(param.getDeptName());
        dataInDB.setParentId(param.getParentId());
        dataInDB.setDisplayIndex(param.getDisplayIndex());
        sysDeptMapper.updateById(dataInDB);
    }

    @Override
    public void deleteById(String id) {
        sysDeptMapper.deleteById(id);
    }
}
