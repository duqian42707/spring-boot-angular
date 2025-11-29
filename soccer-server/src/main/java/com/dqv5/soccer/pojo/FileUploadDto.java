package com.dqv5.soccer.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

@Data
@Schema(name = "上传文件信息")
public class FileUploadDto {
    @SchemaProperty(name = "文件访问URL")
    private String url;
    @SchemaProperty(name = "文件名称")
    private String name;
}
