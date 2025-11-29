package com.dqv5.soccer.web;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.pojo.UserQueryParam;
import com.dqv5.soccer.service.SysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author duqian
 * @date 2022/7/17
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@Tag(name = "用户管理")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @GetMapping("/list")
    @Operation(summary = "获取用户列表")
    public ResponseEntity<RestReturnEntity<PageInfo<SysUser>>> list(UserQueryParam param) {
        PageInfo<SysUser> pageInfo = sysUserService.queryListForPage(param);
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "获取单个用户")
    public ResponseEntity<RestReturnEntity<SysUser>> info(@PathVariable("id") String id) {
        SysUser sysUser = sysUserService.findOne(id);
        return RestReturn.ok(sysUser);
    }

    @PostMapping("/insert")
    @Operation(summary = "新增用户")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_USER_INSERT + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysUser sysUser) {
        sysUserService.insert(sysUser);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "更新用户信息")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_USER_UPDATE + "')")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_USER_DELETE + "')")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysUserService.deleteById(id);
        return RestReturn.ok();
    }

}
