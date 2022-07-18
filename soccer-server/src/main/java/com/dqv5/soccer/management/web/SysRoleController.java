package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.management.entity.SysRole;
import com.dqv5.soccer.management.service.SysRoleService;
import com.dqv5.soccer.pojo.PageInfo;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.utils.SecurityUtils;
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
@RequestMapping("/api/role")
@Slf4j
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取角色列表
     */
    @GetMapping("/list")
    public ResponseEntity<RestReturnEntity<PageInfo<SysRole>>> userList(@RequestParam(defaultValue = "1") int pageNum,
                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        AuthUser userDetail = SecurityUtils.getCurrentUserDetail();
        log.info("{}", userDetail);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.DESC, "lastModifiedDate");
        PageInfo<SysRole> pageInfo = sysRoleService.findAll(pageable);
        return RestReturn.ok(pageInfo);
    }

    /**
     * 获取单个角色
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<RestReturnEntity<SysRole>> userInfo(@PathVariable("id") String id) {
        SysRole result = sysRoleService.findOne(id);
        return RestReturn.ok(result);
    }

    /**
     * 新增角色
     */
    @PostMapping("/insert")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysRole param) {
        sysRoleService.insert(param);
        return RestReturn.ok();
    }

    /**
     * 更新角色信息
     */
    @PostMapping("/update")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysRole param) {
        sysRoleService.update(param);
        return RestReturn.ok();
    }

    /**
     * 删除角色
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysRoleService.deleteById(id);
        return RestReturn.ok();
    }

}
