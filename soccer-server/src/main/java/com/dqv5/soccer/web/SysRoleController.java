package com.dqv5.soccer.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.table.SysRoleTable;
import com.dqv5.soccer.service.SysRoleService;
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
    public ResponseEntity<RestReturnEntity<PageInfo<SysRoleTable>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                         @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.of(pageNum, pageSize);
        PageInfo<SysRoleTable> pageInfo = sysRoleService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个角色")
    public ResponseEntity<RestReturnEntity<SysRoleTable>> info(@PathVariable("id") String id) {
        SysRoleTable result = sysRoleService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @ApiOperation("新增角色")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysRoleTable param) {
        sysRoleService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新角色信息")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysRoleTable param) {
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
