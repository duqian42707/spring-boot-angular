package com.dqv5.soccer.web;

import com.dqv5.soccer.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2022/7/17
 */
@Slf4j
@RestController
public class SysController {


    @GetMapping("/user")
    public Object user() {
        return SecurityUtils.getCurrentUserDetail();
    }
}
