package com.dqv5.soccer.web;

import com.dqv5.soccer.common.ConfigValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.SysInfo;
import com.dqv5.soccer.service.InitDataService;
import com.dqv5.soccer.service.SysConfigService;
import com.dqv5.soccer.table.SysConfigTable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/sys")
@Slf4j
@Api(tags = "系统管理")
public class SysController {
    @Resource
    private InitDataService initDataService;
    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("/info")
    @ApiOperation("获取系统基本信息")
    public ResponseEntity<RestReturnEntity<SysInfo>> info() {
        SysInfo sysInfo = sysConfigService.getSysInfo();
        return RestReturn.ok(sysInfo);
    }

    @PostMapping("/reInitData")
    @ApiOperation("重新初始化系统数据")
    public ResponseEntity<RestReturnEntity<Object>> reInitData() {
        initDataService.initData(true);
        return RestReturn.ok();
    }


}
