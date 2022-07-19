package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.management.entity.SysMenu;
import com.dqv5.soccer.management.service.SysMenuService;
import com.dqv5.soccer.pojo.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/menu")
@Slf4j
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 获取菜单树
     */
    @GetMapping("/tree")
    public ResponseEntity<RestReturnEntity<List<TreeNode>>> tree() {
        List<TreeNode> tree = sysMenuService.findAllTree();
        return RestReturn.ok(tree);
    }

    /**
     * 获取单个菜单
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<RestReturnEntity<SysMenu>> info(@PathVariable("id") String id) {
        SysMenu result = sysMenuService.findOne(id);
        return RestReturn.ok(result);
    }

    /**
     * 新增菜单
     */
    @PostMapping("/insert")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysMenu param) {
        sysMenuService.insert(param);
        return RestReturn.ok();
    }

    /**
     * 更新菜单信息
     */
    @PostMapping("/update")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysMenu param) {
        sysMenuService.update(param);
        return RestReturn.ok();
    }

    /**
     * 删除菜单
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysMenuService.deleteById(id);
        return RestReturn.ok();
    }

}
