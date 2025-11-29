package com.dqv5.soccer.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author admin
 * @date 2022/7/17
 */
@Slf4j
@Controller
@Tag(name = "页面导航")
public class PageController {

    @GetMapping("/")
    @Operation(summary = "首页")
    public String index(HttpServletRequest request) {
        String url = request.getRequestURI();
        url = "redirect:" + url + (url.endsWith("/") ? "" : "/") + "web/";
        log.info("url:{}", url);
        return url;
    }

    @GetMapping("/web/")
    @Operation(summary = "web端页面")
    public String web() {
        return "web/index";
    }
}
