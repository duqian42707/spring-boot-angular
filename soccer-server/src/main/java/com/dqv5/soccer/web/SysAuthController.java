package com.dqv5.soccer.web;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.*;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.pojo.SysAuthFolder;
import com.dqv5.soccer.service.SysAuthService;
import com.dqv5.soccer.table.SysAuthTable;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
@Tag(name = "权限管理")
public class SysAuthController {
    @Resource
    private SysAuthService sysAuthService;

    @GetMapping("/tree")
    @Operation(summary = "获取所有权限", description = "按目录分组展示")
    public ResponseEntity<RestReturnEntity<List<TreeNode>>> tree() {
        List<TreeNode> tree = sysAuthService.findAuthTree();
        return RestReturn.ok(tree);
    }

    @GetMapping("/list")
    @Operation(summary = "获取权限列表")
    public ResponseEntity<RestReturnEntity<PageInfo<SysAuth>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize,
                                                                    String authName, String authValue, String authFolderName) {
        Pageable pageable = Pageable.of(pageNum, pageSize);
        JSONObject param = new JSONObject();
        if (StringUtils.isNotBlank(authName)) {
            param.put("authName", authName);
        }
        if (StringUtils.isNotBlank(authValue)) {
            param.put("authValue", authValue);
        }
        if (StringUtils.isNotBlank(authFolderName)) {
            param.put("authFolderName", authFolderName);
        }
        PageInfo<SysAuth> pageInfo = sysAuthService.queryListForPage(param, pageable);
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/folders")
    @Operation(summary = "获取所有权限", description = "按目录分组展示")
    public ResponseEntity<RestReturnEntity<List<SysAuthFolder>>> folders() {
        List<SysAuthFolder> folders = sysAuthService.findFolders();
        return RestReturn.ok(folders);
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "获取单个权限")
    public ResponseEntity<RestReturnEntity<SysAuthTable>> info(@PathVariable("id") String id) {
        SysAuthTable result = sysAuthService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @Operation(summary = "新增权限")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_AUTH_INSERT + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysAuthTable param) {
        sysAuthService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新权限信息")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_AUTH_UPDATE + "')")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysAuthTable param) {
        sysAuthService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "删除权限")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_AUTH_DELETE + "')")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysAuthService.deleteById(id);
        return RestReturn.ok();
    }

}
