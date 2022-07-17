package com.dqv5.soccer.web;

import com.dqv5.soccer.pojo.entity.BaseUser;
import com.dqv5.soccer.pojo.result.RestReturn;
import com.dqv5.soccer.service.BaseUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author duqian
 * @date 2022/7/17
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class BaseUserController {
    @Resource
    private BaseUserService baseUserService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping("/list")
    public Page<BaseUser> userList(@RequestParam(defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.Direction.ASC, "account");
        return baseUserService.findAll(pageable);
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getInfo(@PathVariable("id") Integer id) {
        return RestReturn.ok("");
    }

    /**
     * 新增、更新用户信息
     *
     * @param baseUser
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody BaseUser baseUser) {
        return RestReturn.ok(baseUser);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return RestReturn.ok();
    }

}
