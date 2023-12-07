package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.management.table.SysRole;
import com.dqv5.soccer.management.service.SysRoleService;
import com.dqv5.soccer.pojo.Pageable;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "角色管理")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @GetMapping("/list")
    @ApiOperation("获取角色列表")
    public ResponseEntity<RestReturnEntity<PageInfo<SysRole>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                    @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.of(pageNum, pageSize);
        PageInfo<SysRole> pageInfo = sysRoleService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个角色")
    public ResponseEntity<RestReturnEntity<SysRole>> info(@PathVariable("id") String id) {
        SysRole result = sysRoleService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @ApiOperation("新增角色")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysRole param) {
        sysRoleService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新角色信息")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysRole param) {
        sysRoleService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除角色")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysRoleService.deleteById(id);
        return RestReturn.ok();
    }

}
