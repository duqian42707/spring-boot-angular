package com.dqv5.soccer.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author admin
 * @date 2022/7/17
 */
@Slf4j
@Controller
public class PageController {

    @GetMapping({"/", "/passport/**", "/dashboard/**", "/sys/**"})
    public String index() {
        return "portal/index";
    }
}
