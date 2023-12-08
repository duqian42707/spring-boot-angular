package com.dqv5.soccer.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2022/7/17
 */
@Slf4j
@Controller
public class PageController {

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        String url = request.getRequestURI();
        url = "redirect:" + url + (url.endsWith("/") ? "" : "/") + "web/";
        log.info("url:{}", url);
        return url;
    }

    @GetMapping("/web/")
    public String web() {
        return "web/index";
    }
}
