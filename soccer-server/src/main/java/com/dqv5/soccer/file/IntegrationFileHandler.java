package com.dqv5.soccer.file;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.InputStream;
import java.util.Objects;

/**
 * @author duq
 * @date 2024/2/17
 */
public interface IntegrationFileHandler {
    /**
     * 上传文件，返回storeInfo
     *
     * @param inputStream
     * @param fileName
     * @return
     */
    String upload(InputStream inputStream, String fileName);

    /**
     * 下载文件，获取输入流
     *
     * @param storeInfo
     * @return
     */
    InputStream getInputStream(String storeInfo);

    /**
     * 删除文件
     *
     * @param storeInfo
     */
    void delete(String storeInfo);

    /**
     * 检查文件一致性
     *
     * @param storeInfo
     * @param sha256
     * @return
     */
    default boolean checkFile(String storeInfo, String sha256) {
        try (InputStream inputStream = this.getInputStream(storeInfo)) {
            String sha256Hex = DigestUtils.sha256Hex(inputStream);
            return Objects.equals(sha256, sha256Hex);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否匹配存储类型
     *
     * @param storeType
     * @return
     */
    boolean support(int storeType);
}
