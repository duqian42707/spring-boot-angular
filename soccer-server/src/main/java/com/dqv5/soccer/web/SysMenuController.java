package com.dqv5.soccer.web;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.pojo.SysMenu;
import com.dqv5.soccer.service.SysMenuService;
import com.dqv5.soccer.table.SysMenuTable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/menu")
@Slf4j
@Tag(name = "菜单管理")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/all")
    @Operation(summary = "获取所有菜单")
    public ResponseEntity<RestReturnEntity<List<SysMenu>>> all() {
        List<SysMenu> tree = sysMenuService.findAll();
        return RestReturn.ok(tree);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取菜单树")
    public ResponseEntity<RestReturnEntity<List<TreeNode>>> tree() {
        List<TreeNode> tree = sysMenuService.findAllTree();
        return RestReturn.ok(tree);
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "获取单个菜单")
    public ResponseEntity<RestReturnEntity<SysMenuTable>> info(@PathVariable("id") String id) {
        SysMenuTable result = sysMenuService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @Operation(summary = "新增菜单")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_MENU_INSERT + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysMenu param) {
        sysMenuService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新菜单信息")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_MENU_UPDATE + "')")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysMenu param) {
        sysMenuService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "删除菜单")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_MENU_DELETE + "')")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysMenuService.deleteById(id);
        return RestReturn.ok();
    }

}
