package com.dqv5.soccer.web.controller;

import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.entity.SysModule;
import com.dqv5.soccer.security.JwtTokenUtil;
import com.dqv5.soccer.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duq
 * @date 2018/9/9
 */
@RestController
@RequestMapping("/sys/frame")
public class SysFrameController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private SysModuleService sysModuleService;

    @GetMapping("/info")
    public ResponseEntity getUserInfo(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        BasicUser user = (BasicUser) userDetailsService.loadUserByUsername(username);
//        List<SysModule> menu = sysModuleService.findListByUser(user.getId());
        List<SysModule> menu = sysModuleService.findList();
        Map map = new HashMap();
        map.put("user", user);
        map.put("menu", menu);
        return ResponseEntity.ok(map);
    }
}
