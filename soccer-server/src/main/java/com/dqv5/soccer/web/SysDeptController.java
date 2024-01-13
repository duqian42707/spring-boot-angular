package com.dqv5.soccer.web;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.SysDept;
import com.dqv5.soccer.service.SysDeptService;
import com.dqv5.soccer.table.SysDeptTable;
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
 * @date 2024/1/13
 */
@RestController
@RequestMapping("/api/dept")
@Slf4j
@Api(tags = "部门管理")
public class SysDeptController {
    @Resource
    private SysDeptService sysDeptService;

    @GetMapping("/all")
    @ApiOperation("获取所有部门")
    public ResponseEntity<RestReturnEntity<List<SysDept>>> all() {
        List<SysDept> pageInfo = sysDeptService.queryAll();
        return RestReturn.ok(pageInfo);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取单个部门")
    public ResponseEntity<RestReturnEntity<SysDept>> info(@PathVariable("id") String id) {
        SysDept result = sysDeptService.findOne(id);
        return RestReturn.ok(result);
    }

    @PostMapping("/insert")
    @ApiOperation("新增部门")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_DEPT_INSERT + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody SysDeptTable param) {
        sysDeptService.insert(param);
        return RestReturn.ok();
    }

    @PostMapping("/update")
    @ApiOperation("更新部门信息")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_DEPT_UPDATE + "')")
    public ResponseEntity<RestReturnEntity<Object>> update(@RequestBody SysDeptTable param) {
        sysDeptService.update(param);
        return RestReturn.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除部门")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_DEPT_DELETE + "')")
    public ResponseEntity<RestReturnEntity<Object>> delete(@PathVariable("id") String id) {
        sysDeptService.deleteById(id);
        return RestReturn.ok();
    }


}
