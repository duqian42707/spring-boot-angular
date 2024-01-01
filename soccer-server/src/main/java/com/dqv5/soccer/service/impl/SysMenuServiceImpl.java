package com.dqv5.soccer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysMenuMapper;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.service.SysMenuService;
import com.dqv5.soccer.table.SysMenuTable;
import com.dqv5.soccer.utils.MenuTreeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findAll() {
        QueryWrapper<SysMenuTable> queryWrapper = Wrappers.query(SysMenuTable.class).orderByAsc("display_index");
        List<SysMenuTable> allMenus = sysMenuMapper.selectList(queryWrapper);
        return MenuTreeUtils.buildTree(allMenus);
    }

    @Override
    public List<TreeNode> findAllTree() {
        QueryWrapper<SysMenuTable> queryWrapper = Wrappers.query(SysMenuTable.class).orderByAsc("display_index");
        List<SysMenuTable> allMenus = sysMenuMapper.selectList(queryWrapper);
        return MenuTreeUtils.buildTree(allMenus).stream().map(SysMenu::toTreeNode).collect(Collectors.toList());
    }

    @Override
    public SysMenuTable findOne(String id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public void insert(SysMenu param) {
        SysMenuTable table = param.toTable();
        table.setMenuId(null);
        String menuCode = table.getMenuCode();
        QueryWrapper<SysMenuTable> queryWrapper = Wrappers.query(SysMenuTable.class).eq("menu_code", menuCode);
        if (sysMenuMapper.exists(queryWrapper)) {
            throw new CommonRuntimeException("菜单编码已存在：" + menuCode);
        }
        sysMenuMapper.insert(table);
    }

    @Override
    public void update(SysMenu param) {
        String menuId = param.getMenuId();
        String menuCode = param.getMenuCode();
        SysMenuTable dataInDB = sysMenuMapper.selectById(menuId);
        QueryWrapper<SysMenuTable> queryWrapper = Wrappers.query(SysMenuTable.class).eq("menu_code", menuCode).ne("menu_id", menuId);
        if (sysMenuMapper.exists(queryWrapper)) {
            throw new CommonRuntimeException("菜单编码已存在：" + menuCode);
        }
        // todo 拷贝属性
        dataInDB.setMenuCode(param.getMenuCode());
        dataInDB.setMenuName(param.getMenuName());
        dataInDB.setLink(param.getLink());
        dataInDB.setExternalLink(param.getExternalLink());
        dataInDB.setIcon(param.getIcon());
        dataInDB.setDisplayIndex(param.getDisplayIndex());
        sysMenuMapper.updateById(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> queryByUserId(String userId) {
        List<SysMenuTable> menuTables = sysMenuMapper.queryByUserId(userId);
        return MenuTreeUtils.buildTree(menuTables);
    }
}
