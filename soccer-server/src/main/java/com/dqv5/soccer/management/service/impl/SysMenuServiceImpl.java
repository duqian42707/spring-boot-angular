package com.dqv5.soccer.management.service.impl;

import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.management.entity.SysMenu;
import com.dqv5.soccer.management.repository.SysMenuRepository;
import com.dqv5.soccer.management.service.SysMenuService;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.pojo.TreeNode;
import com.dqv5.soccer.utils.MenuTreeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuRepository sysMenuRepository;

    @Override
    public PageInfo<SysMenu> queryListForPage(Pageable pageable) {
        Page<SysMenu> page = sysMenuRepository.findAll(pageable);
        return PageInfo.of(page.getTotalElements(), page.getContent());
    }

    @Override
    public SysMenu findOne(String id) {
        return sysMenuRepository.findById(id).orElseThrow(() -> new CommonRuntimeException("菜单id不存在:" + id));
    }

    @Override
    public void insert(SysMenu param) {
        param.setMenuId(null);
        String menuCode = param.getMenuCode();
        if (sysMenuRepository.existsByMenuCode(menuCode)) {
            throw new CommonRuntimeException("菜单编码已存在：" + menuCode);
        }
        sysMenuRepository.save(param);
    }

    @Override
    public void update(SysMenu param) {
        String menuId = param.getMenuId();
        String menuCode = param.getMenuCode();
        SysMenu dataInDB = sysMenuRepository.findById(menuId).orElseThrow(() -> new CommonRuntimeException("菜单id不存在:" + menuId));
        if (sysMenuRepository.existsByMenuCodeAndMenuIdNot(menuCode, menuId)) {
            throw new CommonRuntimeException("菜单编码已存在：" + menuCode);
        }
        // todo 拷贝属性
        dataInDB.setMenuCode(param.getMenuCode());
        dataInDB.setMenuName(param.getMenuName());
        sysMenuRepository.save(dataInDB);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        sysMenuRepository.deleteById(id);
    }

    @Override
    public List<SysMenu> findAllTree() {
        return sysMenuRepository.findAllByParentMenu(null);
    }


}
