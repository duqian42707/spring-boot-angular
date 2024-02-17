package com.dqv5.soccer.file;

import java.io.InputStream;

/**
 * @author duq
 * @date 2024/2/17
 */
public interface IntegrationFileHandler {
    /**
     * 上传文件，返回storeInfo
     * @param inputStream
     * @param fileName
     * @return
     */
    String upload(InputStream inputStream, String fileName);

    /**
     * 下载文件，获取输入流
     * @param storeInfo
     * @return
     */
    InputStream getInputStream(String storeInfo);

    /**
     * 删除文件
     * @param storeInfo
     */
    void delete(String storeInfo);

    /**
     * 是否匹配存储类型
     * @param storeType
     * @return
     */
    boolean support(int storeType);
}
