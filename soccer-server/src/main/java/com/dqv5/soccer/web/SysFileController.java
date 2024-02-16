package com.dqv5.soccer.web;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.pojo.FileUploadDto;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.pojo.SysFile;
import com.dqv5.soccer.service.SysFileService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author duqian
 * @date 2024/1/23
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
@Api(tags = "文件管理")
public class SysFileController {
    @Resource
    private SysFileService sysFileService;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件", notes = "返回文件地址")
    public ResponseEntity<RestReturnEntity<SysFile>> uploadFile(@RequestParam MultipartFile file) {
        SysFile sysFile = sysFileService.uploadFile(file);
        return RestReturn.ok(sysFile);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("获取文件信息")
    public ResponseEntity<RestReturnEntity<SysFile>> getFileInfo(@PathVariable String id) {
        SysFile sysFile = sysFileService.getFileInfo(id);
        return RestReturn.ok(sysFile);
    }

    @GetMapping("/file/{id}")
    @ApiOperation("获取文件")
    public void getFile(@PathVariable String id, String dl, HttpServletResponse response) {
        SysFile sysFile = sysFileService.getFileInfo(id);
        // todo download file
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除文件")
    public ResponseEntity<RestReturnEntity<Object>> deleteFile(@PathVariable String id) {
        sysFileService.deleteFile(id);
        return RestReturn.ok();
    }


}
