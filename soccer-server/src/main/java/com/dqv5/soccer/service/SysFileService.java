package com.dqv5.soccer.service;

import com.dqv5.soccer.pojo.SysFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author duqian
 * @date 2024/1/23
 */

public interface SysFileService {

    SysFile uploadFile(MultipartFile file);

    SysFile getFileInfo(String id);

    void deleteFile(String url);

}
