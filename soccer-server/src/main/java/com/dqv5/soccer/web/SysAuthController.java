package com.dqv5.soccer.web;

import com.dqv5.soccer.common.*;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.pojo.SysAuthFolder;
import com.dqv5.soccer.service.SysAuthService;
import com.dqv5.soccer.table.SysAuthTable;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/tree")
    @ApiOperation(value = "获取所有权限", notes = "按目录分组展示")
    public ResponseEntity<RestReturnEntity<List<TreeNode>>> tree() {
        List<TreeNode> tree = sysAuthService.findAuthTree();
        return RestReturn.ok(tree);
    }

    @GetMapping("/list")
    @ApiOperation("获取权限列表")
    public ResponseEntity<RestReturnEntity<PageInfo<SysAuth>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.of(pageNum, pageSize);
        PageInfo<SysAuth> pageInfo = sysAuthService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/folders")
    @ApiOperation(value = "获取所有权限", notes = "按目录分组展示")
    public ResponseEntity<RestReturnEntity<List<SysAuthFolder>>> folders() {
        List<SysAuthFolder> folders = sysAuthService.findFolders();
        return RestReturn.ok(folders);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个权限")
    public ResponseEntity<RestReturnEntity<SysAuthTable>> info(@PathVariable("id") String id) {
        SysAuthTable result = sysAuthService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @ApiOperation("新增权限")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_AUTH_INSERT + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysAuthTable param) {
        sysAuthService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新权限信息")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_AUTH_UPDATE + "')")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysAuthTable param) {
        sysAuthService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除权限")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_AUTH_DELETE + "')")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysAuthService.deleteById(id);
        return RestReturn.ok();
    }

}
