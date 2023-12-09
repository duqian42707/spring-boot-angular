package com.dqv5.soccer.web;

import com.dqv5.soccer.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2022/7/17
 */
@Slf4j
@RestController
@Api(tags = "系统接口")
public class SysController {

    @GetMapping("/api/user")
    @ApiOperation("获取用户信息")
    public Object user() {
        return SecurityUtils.getCurrentUserDetail();
    }
}
