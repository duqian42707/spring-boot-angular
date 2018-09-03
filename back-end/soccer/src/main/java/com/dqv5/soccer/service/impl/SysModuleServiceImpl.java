package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.dao.SysModuleMapper;
import com.dqv5.soccer.entity.SysModule;
import com.dqv5.soccer.entity.TreeNode;
import com.dqv5.soccer.service.SysModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author duq
 * @date 2018/8/18
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

    @Resource
    private SysModuleMapper sysModuleMapper;

    @Override
    public List<SysModule> findList() {
        List<SysModule> list = sysModuleMapper.findList();
        List<SysModule> result = new ArrayList<>();
        for (SysModule sysModule : list) {
            if (sysModule.getParentId() == null) {
                pushChildren(sysModule, list);
                sysModule.setExpand(true);
                result.add(sysModule);
            }
        }
        return result;
    }

    @Override
    public SysModule findOne(Integer id) {
        return sysModuleMapper.findOne(id);
    }

    @Override
    public SysModule save(SysModule sysModule) {
        if (sysModule.getId() == null) {
            sysModuleMapper.insert(sysModule);
        } else {
            sysModuleMapper.update(sysModule);
        }
        return sysModule;
    }

    @Override
    public void delete(Integer id) {
        sysModuleMapper.delete(id);
    }

    private void pushChildren(SysModule module, List<SysModule> allList) {
        List<TreeNode> children = new ArrayList<>();
        for (SysModule sysModule : allList) {
            if (module.getId().equals(sysModule.getParentId())) {
                pushChildren(sysModule, allList);
                children.add(sysModule);
            }
        }
        module.setChildren(children);
    }
}
