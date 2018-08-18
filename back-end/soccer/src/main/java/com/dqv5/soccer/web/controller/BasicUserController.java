package com.dqv5.soccer.web.controller;

import com.dqv5.soccer.entity.BasicUser;
import com.dqv5.soccer.security.JwtTokenUtil;
import com.dqv5.soccer.service.BasicUserService;
import com.dqv5.soccer.web.result.RestReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2018/7/7
 */
@RestController
@RequestMapping("/basic/user")
public class BasicUserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private BasicUserService basicUserService;

    @GetMapping("/list")
    public ResponseEntity userList() {
        List<BasicUser> all = basicUserService.findAll();
        return RestReturn.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity getInfo(@PathVariable("id") Integer id) {
        BasicUser basicUser = basicUserService.findOne(id);
        return RestReturn.ok(basicUser);
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody BasicUser basicUser) {
        basicUser = basicUserService.insert(basicUser);
        return RestReturn.ok(basicUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delte(@PathVariable("id") Integer id) {
        basicUserService.delete(id);
        return RestReturn.ok();
    }


}
