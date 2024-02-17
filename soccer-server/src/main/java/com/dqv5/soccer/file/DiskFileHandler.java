package com.dqv5.soccer.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author duq
 * @date 2024/2/17
 */
@Component
@Slf4j
public class DiskFileHandler implements IntegrationFileHandler {

    @Override
    public String upload(InputStream inputStream, String fileName) {
        return null;
    }

    @Override
    public InputStream getInputStream(String storeInfo) {
        return null;
    }

    @Override
    public void delete(String storeInfo) {

    }

    @Override
    public boolean support(int storeType) {
        return FileStoreType.Disk.getValue() == storeType;
    }
}
