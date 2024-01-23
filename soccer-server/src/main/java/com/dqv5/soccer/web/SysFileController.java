package com.dqv5.soccer.web;

import com.alibaba.fastjson.JSONObject;
import com.dqv5.soccer.common.RestReturn;
import com.dqv5.soccer.common.RestReturnEntity;
import com.dqv5.soccer.common.TreeNode;
import com.dqv5.soccer.pojo.FileUploadDto;
import com.dqv5.soccer.pojo.SysAuth;
import com.dqv5.soccer.service.SysFileService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    public ResponseEntity<RestReturnEntity<FileUploadDto>> uploadFile(@RequestParam MultipartFile file) {
        FileUploadDto fileUploadDto = sysFileService.uploadFile(file);
        return RestReturn.ok(fileUploadDto);
    }

    @PostMapping("/delete")
    @ApiOperation("删除文件")
    public ResponseEntity<RestReturnEntity<PageInfo<SysAuth>>> deleteFile(@RequestBody JSONObject param) {
        String url = param.getString("url");
        sysFileService.deleteFile(url);
        return RestReturn.ok();
    }


}
