package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.management.entity.SysUser;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.management.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author duqian
 * @date 2022/7/17
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<RestReturnEntity<PageInfo<SysUser>>> userList(@RequestParam(defaultValue = "1") int pageNum,
                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "account");
        PageInfo<SysUser> pageInfo = sysUserService.findAll(pageable);
        return RestReturn.ok(pageInfo);
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<RestReturnEntity<SysUser>> userInfo(@PathVariable("id") String id) {
        SysUser sysUser = sysUserService.findOne(id);
        return RestReturn.ok(sysUser);
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/insert")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysUser sysUser) {
        sysUserService.insert(sysUser);
        return RestReturn.ok();
    }

    /**
     * 更新用户信息
     *
     * @param sysUser
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return RestReturn.ok();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysUserService.deleteById(id);
        return RestReturn.ok();
    }

}
