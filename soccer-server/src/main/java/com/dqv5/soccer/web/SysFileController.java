package com.dqv5.soccer.web;

import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.pojo.SysFile;
import com.dqv5.soccer.service.SysFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duqian
 * @date 2024/1/23
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
@Tag(name = "文件管理")
public class SysFileController {
    @Resource
    private SysFileService sysFileService;

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "返回文件地址")
    public ResponseEntity<RestReturnEntity<SysFile>> uploadFile(@RequestParam MultipartFile file) {
        SysFile sysFile = sysFileService.uploadFile(file);
        return RestReturn.ok(sysFile);
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "获取文件信息")
    public ResponseEntity<RestReturnEntity<SysFile>> getFileInfo(@PathVariable String id) {
        SysFile sysFile = sysFileService.getFileInfo(id);
        return RestReturn.ok(sysFile);
    }

    @GetMapping("/file/{id}")
    @Operation(summary = "获取文件")
    public void downloadFile(@PathVariable String id, String dl, HttpServletResponse response) {
        sysFileService.downloadFile(id, dl, response);
    }

    @PostMapping("/delete/{id}")
    @Operation(summary = "删除文件")
    public ResponseEntity<RestReturnEntity<Object>> deleteFile(@PathVariable String id) {
        sysFileService.deleteFile(id);
        return RestReturn.ok();
    }


}
