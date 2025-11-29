package com.dqv5.soccer.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.ChangePasswordParam;
import com.dqv5.soccer.pojo.UpdateProfileParam;
import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.service.SysProfileService;
import com.dqv5.soccer.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @date 2024/1/22
 */
@Slf4j
@RestController
@Tag(name = "用户信息接口")
@RequestMapping("/api/profile")
public class SysProfileController {
    @Resource
    private SysProfileService sysProfileService;

    @GetMapping("/userInfo")
    @Operation(summary = "获取用户信息")
    public ResponseEntity<RestReturnEntity<AuthUser>> userInfo() {
        AuthUser authUser = SecurityUtils.getCurrentUserDetail();
        return RestReturn.ok(authUser);
    }

    @PostMapping("/updateProfile")
    @Operation(summary = "更新用户信息")
    public ResponseEntity<RestReturnEntity<Object>> updateProfile(@RequestBody UpdateProfileParam param) {
        sysProfileService.updateProfile(param);
        return RestReturn.ok();
    }

    @PostMapping("/changePassword")
    @Operation(summary = "用户修改密码")
    public ResponseEntity<RestReturnEntity<Object>> changePassword(@RequestBody ChangePasswordParam param) {
        sysProfileService.changePassword(param);
        return RestReturn.ok();
    }

}
