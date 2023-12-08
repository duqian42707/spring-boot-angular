package com.dqv5.soccer.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.table.SysMenu;
import com.dqv5.soccer.management.mapper.SysMenuMapper;
import com.dqv5.soccer.management.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public PageInfo<SysMenu> queryListForPage(Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        List<SysMenu> list = sysMenuMapper.selectList(null);
        return new PageInfo<>(list);
    }

    @Override
    public SysMenu findOne(String id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public void insert(SysMenu param) {
        param.setMenuId(null);
        String menuCode = param.getMenuCode();
        QueryWrapper<SysMenu> queryWrapper = Wrappers.query(SysMenu.class).eq("menu_code", menuCode);
        if (sysMenuMapper.exists(queryWrapper)) {
            throw new CommonRuntimeException("菜单编码已存在：" + menuCode);
        }
        sysMenuMapper.insert(param);
    }

    @Override
    public void update(SysMenu param) {
        String menuId = param.getMenuId();
        String menuCode = param.getMenuCode();
        SysMenu dataInDB = sysMenuMapper.selectById(menuId);
        QueryWrapper<SysMenu> queryWrapper = Wrappers.query(SysMenu.class).eq("menu_code", menuCode).ne("menu_id", menuId);
        if (sysMenuMapper.exists(queryWrapper)) {
            throw new CommonRuntimeException("菜单编码已存在：" + menuCode);
        }
        // todo 拷贝属性
        dataInDB.setMenuCode(param.getMenuCode());
        dataInDB.setMenuName(param.getMenuName());
        sysMenuMapper.updateById(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> findAllTree() {
        QueryWrapper<SysMenu> queryWrapper = Wrappers.query(SysMenu.class).isNull("parent_id");
        return sysMenuMapper.selectList(queryWrapper);
    }


}
