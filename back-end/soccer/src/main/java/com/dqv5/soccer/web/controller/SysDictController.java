package com.dqv5.soccer.web.controller;

import com.dqv5.soccer.entity.SysDict;
import com.dqv5.soccer.service.SysDictService;
import com.dqv5.soccer.web.result.RestReturn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 * @date 2018/9/10
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController {
    @Resource
    private SysDictService sysDictService;

    /**
     * 获取列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity list() {
        List<SysDict> list = sysDictService.findList();
        return RestReturn.ok(list);
    }


    /**
     * 新增、更新信息
     *
     * @param sysDict
     * @return
     */
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody SysDict sysDict) {
        sysDictService.save(sysDict);
        return RestReturn.ok();
    }

    /**
     * 删除
     *
     * @param code
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        sysDictService.delete(id);
        return RestReturn.ok();
    }
    /**
     * 删除
     *
     * @param code
     * @return
     */
    @DeleteMapping("/deleteByCode/{code}")
    public ResponseEntity deleteByCode(@PathVariable("code") String code) {
        sysDictService.deleteByCode(code);
        return RestReturn.ok();
    }
}
