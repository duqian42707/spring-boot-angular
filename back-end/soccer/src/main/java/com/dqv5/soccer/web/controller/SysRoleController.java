package com.dqv5.soccer.web.controller;

import com.dqv5.soccer.entity.SysRole;
import com.dqv5.soccer.service.SysRoleService;
import com.dqv5.soccer.web.result.RestReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色管理
 *
 * @author admin
 * @date 2018/8/20
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity list() {
        return RestReturn.ok();
    }

    /**
     * 获取单个信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getInfo(@PathVariable("id") Integer id) {
        SysRole sysRole = sysRoleService.findOne(id);
        return RestReturn.ok(sysRole);
    }

    /**
     * 新增、更新信息
     *
     * @param sysRole
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SysRole sysRole) {
        sysRole = sysRoleService.save(sysRole);
        return RestReturn.ok(sysRole);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delte(@PathVariable("id") Integer id) {
        sysRoleService.delete(id);
        return RestReturn.ok();
    }
}
