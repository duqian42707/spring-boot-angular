package com.dqv5.soccer.service.impl;

import com.dqv5.soccer.dao.SysFuncRightMapper;
import com.dqv5.soccer.dao.SysModuleMapper;
import com.dqv5.soccer.entity.SysFuncRight;
import com.dqv5.soccer.entity.SysModule;
import com.dqv5.soccer.entity.TreeNode;
import com.dqv5.soccer.service.SysModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duq
 * @date 2018/8/18
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

    @Resource
    private SysModuleMapper sysModuleMapper;
    @Resource
    private SysFuncRightMapper sysFuncRightMapper;

    @Override
    public List<SysModule> findList() {
        List<SysModule> list = sysModuleMapper.findList();
        return moduleListToTree(list);
    }

    @Override
    public List<SysModule> findListByUser(int userId) {
        //查询出所有模块
        List<SysModule> allModules = sysModuleMapper.findList();
        //查询用户角色直接关联的模块（叶子节点）
        List<SysModule> list = sysModuleMapper.findListByUser(userId);
        List result = getNodesWithParents(list, allModules);
        return moduleListToTree(result);
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
        if (sysModule.getFuncRightList() != null) {
            sysFuncRightMapper.deleteByModule(sysModule.getId());
            for (SysFuncRight sysFuncRight : sysModule.getFuncRightList()) {
                sysFuncRightMapper.insert(sysFuncRight);
            }
        }
        return sysModule;
    }

    @Override
    public void delete(Integer id) {
        sysModuleMapper.delete(id);
    }


    private List<SysModule> moduleListToTree(List<SysModule> list) {
        List<SysModule> result = new ArrayList<>();
        for (SysModule sysModule : list) {
            if (sysModule.getParentId() == null) {
                pushChildren(sysModule, list);
                sysModule.setExpand("1");
                result.add(sysModule);
            }
        }
        return result;
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


    Map<Integer, SysModule> mapResult;

    private List<SysModule> getNodesWithParents(List<SysModule> list, List<SysModule> allModules) {
        mapResult = new HashMap<>();
        for (SysModule sysModule : list) {
            getNodesWithParents(sysModule, allModules);
        }
        List<SysModule> result = new ArrayList<>(mapResult.values());
        return result;
    }

    private void getNodesWithParents(SysModule module, List<SysModule> allModules) {
        mapResult.putIfAbsent(module.getId(), module);
        if (module.getParentId() == null) {
            return;
        }
        for (SysModule item : allModules) {
            if (item.getId().equals(module.getParentId())) {
                mapResult.putIfAbsent(item.getId(), item);
                getNodesWithParents(item, allModules);
                break;
            }
        }
    }
}
