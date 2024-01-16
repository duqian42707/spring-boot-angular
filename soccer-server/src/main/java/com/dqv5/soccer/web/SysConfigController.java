package com.dqv5.soccer.web;

import com.dqv5.soccer.service.SysConfigService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
