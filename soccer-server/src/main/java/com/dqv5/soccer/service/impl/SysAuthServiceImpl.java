package com.dqv5.soccer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysAuthFolderMapper;
import com.dqv5.soccer.mapper.SysAuthMapper;
import com.dqv5.soccer.common.Pageable;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.pojo.SysAuthFolder;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.service.SysAuthService;
import com.dqv5.soccer.table.SysAuthFolderTable;
import com.dqv5.soccer.table.SysAuthTable;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SysAuthServiceImpl implements SysAuthService {
    @Resource
    private SysAuthFolderMapper sysAuthFolderMapper;
    @Resource
    private SysAuthMapper sysAuthMapper;

    private static final String DEFAULT_FOLDER_ID = "999999";

    @Override
    public List<TreeNode> findAuthTree() {
        QueryWrapper<SysAuthFolderTable> folderQuery = Wrappers.query(SysAuthFolderTable.class).orderByAsc("display_index");
        List<SysAuthFolderTable> folders = sysAuthFolderMapper.selectList(folderQuery);
        QueryWrapper<SysAuthTable> authQuery = Wrappers.query(SysAuthTable.class).orderByAsc("display_index");
        List<SysAuthTable> auths = sysAuthMapper.selectList(authQuery);

        folders.add(SysAuthFolderTable.builder().authFolderId(DEFAULT_FOLDER_ID).authFolderName("未分类").displayIndex(999999).build());
        return folders.stream().map(SysAuthFolder::of)
                .peek(x -> {
                    List<SysAuth> filteredAuths = auths.stream()
                            .filter(auth -> {
                                String folderId = StringUtils.isNotBlank(auth.getAuthFolderId()) ? auth.getAuthFolderId() : DEFAULT_FOLDER_ID;
                                return Objects.equals(x.getAuthFolderId(), folderId);
                            })
                            .map(SysAuth::of)
                            .collect(Collectors.toList());
                    x.setAuths(filteredAuths);
                })
                .filter(x -> !CollectionUtils.isEmpty(x.getAuths()))
                .map(SysAuthFolder::toTreeNode)
                .collect(Collectors.toList());
    }

    @Override
    public PageInfo<SysAuth> queryListForPage(JSONObject param, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysAuth> list = sysAuthMapper.queryList(param);
        return new PageInfo<>(list);
    }

    @Override
    public List<SysAuthFolder> findFolders() {
        List<SysAuthFolderTable> list = sysAuthFolderMapper.selectList(null);
        return list.stream().map(SysAuthFolder::of).collect(Collectors.toList());
    }

    @Override
    public SysAuthTable findOne(String id) {
        return sysAuthMapper.selectById(id);
    }

    @Override
    public void insert(SysAuthTable param) {
        param.setAuthId(null);
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        QueryWrapper<SysAuthTable> query1 = Wrappers.query(SysAuthTable.class).eq("auth_value", authValue);
        if (sysAuthMapper.exists(query1)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        QueryWrapper<SysAuthTable> query2 = Wrappers.query(SysAuthTable.class).eq("auth_name", authName);
        if (sysAuthMapper.exists(query2)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        sysAuthMapper.insert(param);
    }

    @Override
    public void update(SysAuthTable param) {
        String authId = param.getAuthId();
        String authValue = param.getAuthValue();
        String authName = param.getAuthName();
        SysAuthTable dataInDB = sysAuthMapper.selectById(authId);
        QueryWrapper<SysAuthTable> query1 = Wrappers.query(SysAuthTable.class).eq("auth_value", authValue).ne("auth_id", authId);
        if (sysAuthMapper.exists(query1)) {
            throw new CommonRuntimeException("权限标识已存在：" + authValue);
        }
        QueryWrapper<SysAuthTable> query2 = Wrappers.query(SysAuthTable.class).eq("auth_name", authName).ne("auth_id", authId);
        if (sysAuthMapper.exists(query2)) {
            throw new CommonRuntimeException("权限名称已存在：" + authName);
        }
        dataInDB.setAuthValue(param.getAuthValue());
        dataInDB.setAuthName(param.getAuthName());
        dataInDB.setAuthFolderId(param.getAuthFolderId());
        sysAuthMapper.updateById(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysAuthMapper.deleteById(id);
    }

}
