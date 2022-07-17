package com.dqv5.soccer.web;

import com.dqv5.soccer.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2022/7/17
 */
@Slf4j
@Controller
public class PageController {

    @GetMapping({"/", "/passport/**", "/dashboard/**", "/base/**"})
    public String index() {
        return "portal/index";
    }
}
