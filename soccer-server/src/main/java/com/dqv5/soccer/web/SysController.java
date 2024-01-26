package com.dqv5.soccer.web;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.service.InitDataService;
import com.dqv5.soccer.service.SysConfigService;
import com.dqv5.soccer.table.SysConfigTable;
import com.dqv5.soccer.utils.SecurityUtils;
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
    @ApiOperation("获取系统配置信息及用户信息")
    public ResponseEntity<RestReturnEntity<Object>> info() {
        JSONObject result = new JSONObject();
        List<SysConfigTable> list = sysConfigService.queryAll();
        JSONObject configMap = new JSONObject();
        for (SysConfigTable sysConfigTable : list) {
            configMap.put(sysConfigTable.getConfigKey(), sysConfigTable.getConfigValue());
        }
        result.put("config", configMap);
        AuthUser authUser = SecurityUtils.getCurrentUserDetail();
        result.put("user", authUser);
        return RestReturn.ok(result);
    }

    @PostMapping("/reInitData")
    @ApiOperation("重新初始化系统数据")
    public ResponseEntity<RestReturnEntity<Object>> reInitData() {
        initDataService.initData(true);
        return RestReturn.ok();
    }


}
