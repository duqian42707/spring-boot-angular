package com.dqv5.soccer.web;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.SysUser;
import com.dqv5.soccer.service.SysUserService;
import com.dqv5.soccer.table.SysUserTable;
import com.dqv5.soccer.common.Pageable;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author duqian
 * @date 2022/7/17
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@Api(tags = "用户管理")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    @GetMapping("/list")
    @ApiOperation("获取用户列表")
    public ResponseEntity<RestReturnEntity<PageInfo<SysUser>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.of(pageNum, pageSize);
        PageInfo<SysUser> pageInfo = sysUserService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个用户")
    public ResponseEntity<RestReturnEntity<SysUserTable>> info(@PathVariable("id") String id) {
        SysUserTable sysUserTable = sysUserService.findOne(id);
        return RestReturn.ok(sysUserTable);
    }

    @PostMapping("/insert")
    @ApiOperation("新增用户")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_USER_INSERT + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysUserTable sysUserTable) {
        sysUserService.insert(sysUserTable);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新用户信息")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_USER_UPDATE + "')")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysUserTable sysUserTable) {
        sysUserService.update(sysUserTable);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除用户")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_USER_DELETE + "')")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysUserService.deleteById(id);
        return RestReturn.ok();
    }

}
