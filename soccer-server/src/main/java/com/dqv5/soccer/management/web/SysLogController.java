package com.dqv5.soccer.management.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.management.table.SysLog;
import com.dqv5.soccer.management.service.SysLogService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import com.dqv5.soccer.pojo.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/log")
@Slf4j
public class SysLogController {
    @Resource
    private SysLogService sysLogService;

    /**
     * 获取日志列表
     */
    @GetMapping("/list")
    public ResponseEntity<RestReturnEntity<PageInfo<SysLog>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                   @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.of(pageNum , pageSize);
        PageInfo<SysLog> pageInfo = sysLogService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }


}
