package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.FileUploadDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duqian
 * @date 2024/1/23
 */

public interface SysFileService {

    FileUploadDto uploadFile(MultipartFile file);

    void deleteFile(String url);
}
