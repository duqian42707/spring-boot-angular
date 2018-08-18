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

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity userList() {
        List<BasicUser> all = basicUserService.findAll();
        return RestReturn.ok(all);
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getInfo(@PathVariable("id") Integer id) {
        BasicUser basicUser = basicUserService.findOne(id);
        return RestReturn.ok(basicUser);
    }

    /**
     * 新增、更新用户信息
     *
     * @param basicUser
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody BasicUser basicUser) {
        basicUser = basicUserService.save(basicUser);
        return RestReturn.ok(basicUser);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delte(@PathVariable("id") Integer id) {
        basicUserService.delete(id);
        return RestReturn.ok();
    }


}
