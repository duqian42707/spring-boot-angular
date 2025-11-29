package com.dqv5.soccer.web;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.SysInfo;
import com.dqv5.soccer.service.InitDataService;
import com.dqv5.soccer.service.SysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/sys")
@Slf4j
@Tag(name = "系统管理")
public class SysController {
    @Resource
    private InitDataService initDataService;
    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("/info")
    @Operation(summary = "获取系统基本信息")
    public ResponseEntity<RestReturnEntity<SysInfo>> info() {
        SysInfo sysInfo = sysConfigService.getSysInfo();
        return RestReturn.ok(sysInfo);
    }

    @PostMapping("/reInitData")
    @Operation(summary = "重新初始化系统数据")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_RE_INIT_DATA + "')")
    public ResponseEntity<RestReturnEntity<Object>> reInitData() {
        initDataService.initData(true);
        return RestReturn.ok();
    }


}
