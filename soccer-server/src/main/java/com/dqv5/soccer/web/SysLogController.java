package com.dqv5.soccer.web;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.table.SysLogTable;
import com.dqv5.soccer.service.SysLogService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import com.dqv5.soccer.common.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author duqian
 * @date 2022/7/19
 */
@RestController
@RequestMapping("/api/log")
@Tag(name = "系统日志")
@Slf4j
public class SysLogController {
    @Resource
    private SysLogService sysLogService;

    @GetMapping("/list")
    @Operation(summary = "获取日志列表")
    @PreAuthorize("hasAuthority('" + AuthValue.SYS_LOG_QUERY + "')")
    public ResponseEntity<RestReturnEntity<PageInfo<SysLogTable>>> list(@RequestParam(defaultValue = "1") int pageNum,
                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = Pageable.of(pageNum, pageSize);
        PageInfo<SysLogTable> pageInfo = sysLogService.queryListForPage(pageable);
        return RestReturn.ok(pageInfo);
    }


}
