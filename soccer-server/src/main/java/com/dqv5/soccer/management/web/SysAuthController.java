package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.management.table.SysAuth;
import com.dqv5.soccer.management.service.SysAuthService;
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
@RequestMapping("/api/auth")
@Slf4j
@Api(tags = "权限管理")
public class SysAuthController {
    @Resource
    private SysAuthService sysAuthService;

    @GetMapping("/all")
    @ApiOperation("获取菜单下的所有权限")
    public ResponseEntity<RestReturnEntity<List<SysAuth>>> all(@RequestParam String menuId) {
        List<SysAuth> tree = sysAuthService.findAll(menuId);
        return RestReturn.ok(tree);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个权限")
    public ResponseEntity<RestReturnEntity<SysAuth>> info(@PathVariable("id") String id) {
        SysAuth result = sysAuthService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @ApiOperation("新增权限")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysAuth param) {
        sysAuthService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新权限信息")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysAuth param) {
        sysAuthService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除权限")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysAuthService.deleteById(id);
        return RestReturn.ok();
    }

}
