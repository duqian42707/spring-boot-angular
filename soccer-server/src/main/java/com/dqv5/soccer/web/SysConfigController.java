package com.dqv5.soccer.web;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.service.SysConfigService;
import com.dqv5.soccer.table.SysConfigTable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duq
 * @date 2024/1/16
 */
@RestController
@Api(tags = "系统配置")
@RequestMapping("/api/config")
@Slf4j
public class SysConfigController {
    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("/allList")
    @ApiOperation("获取所有配置")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_CONFIG_QUERY + "')")
    public ResponseEntity<RestReturnEntity<List<SysConfigTable>>> allList() {
        List<SysConfigTable> list = sysConfigService.queryAll();
        return RestReturn.ok(list);
    }

    @GetMapping("/allMap")
    @ApiOperation("获取所有配置")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_CONFIG_QUERY + "')")
    public ResponseEntity<RestReturnEntity<JSONObject>> allMap() {
        List<SysConfigTable> list = sysConfigService.queryAll();
        JSONObject result = new JSONObject();
        for (SysConfigTable sysConfigTable : list) {
            result.put(sysConfigTable.getConfigKey(), sysConfigTable.getConfigValue());
        }
        return RestReturn.ok(result);
    }

    @PostMapping("/set")
    @ApiOperation("保存配置")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_CONFIG_SET + "')")
    public ResponseEntity<RestReturnEntity<Object>> insert(@RequestBody JSONObject param) {
        sysConfigService.setConfig(param);
        return RestReturn.ok();
    }

}
