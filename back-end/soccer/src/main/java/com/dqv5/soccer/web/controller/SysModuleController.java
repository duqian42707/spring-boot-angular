package com.dqv5.soccer.web.controller;

import com.dqv5.soccer.entity.SysModule;
import com.dqv5.soccer.service.SysModuleService;
import com.dqv5.soccer.web.result.RestReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模块管理
 *
 * @author duq
 * @date 2018/8/18
 */
@RestController
@RequestMapping("/sys/module")
public class SysModuleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SysModuleService sysModuleService;

    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity list() {
        List<SysModule> list = sysModuleService.findList();
        return RestReturn.ok(list);
    }

    /**
     * 获取单个信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity getInfo(@PathVariable("id") Integer id) {
        SysModule sysModule = sysModuleService.findOne(id);
        return RestReturn.ok(sysModule);
    }

    /**
     * 新增、更新信息
     *
     * @param sysModule
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SysModule sysModule) {
        sysModule = sysModuleService.save(sysModule);
        return RestReturn.ok(sysModule);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delte(@PathVariable("id") Integer id) {
        sysModuleService.delete(id);
        return RestReturn.ok();
    }
}
