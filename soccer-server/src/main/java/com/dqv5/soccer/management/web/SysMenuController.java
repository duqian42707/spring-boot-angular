package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.management.table.SysMenu;
import com.dqv5.soccer.management.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "菜单管理")
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;

    @GetMapping("/tree")
    @ApiOperation("获取菜单树")
    public ResponseEntity<RestReturnEntity<List<SysMenu>>> tree() {
        List<SysMenu> tree = sysMenuService.findAllTree();
        return RestReturn.ok(tree);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个菜单")
    public ResponseEntity<RestReturnEntity<SysMenu>> info(@PathVariable("id") String id) {
        SysMenu result = sysMenuService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @ApiOperation("新增菜单")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysMenu param) {
        sysMenuService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新菜单信息")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysMenu param) {
        sysMenuService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除菜单")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysMenuService.deleteById(id);
        return RestReturn.ok();
    }

}
