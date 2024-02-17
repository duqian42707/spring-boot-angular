package com.dqv5.soccer.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dqv5.soccer.file.AmazonS3Properties;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.file.FileStoreType;
import com.dqv5.soccer.file.IntegrationFileHandler;
import com.dqv5.soccer.mapper.SysFileMapper;
import com.dqv5.soccer.pojo.SysFile;
import com.dqv5.soccer.service.SysFileService;
import com.dqv5.soccer.table.SysFileTable;
import com.dqv5.soccer.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * @author duqian
 * @date 2024/1/23
 */
@Service
@Slf4j
public class SysFileServiceImpl implements SysFileService {
    @Resource
    private SysFileMapper sysFileMapper;
    @Resource
    private List<IntegrationFileHandler> fileHandlers;
    @Value("${soccer.file-store.default:disk}")
    private String defaultStoreType;

    @Override
    public SysFile uploadFile(MultipartFile file) {
        SysFileTable sysFileTable = new SysFileTable();
        String fileName = file.getOriginalFilename();
        int storeTypeValue = FileStoreType.fromName(defaultStoreType).getValue();
        IntegrationFileHandler fileHandler = this.getFileHandler(storeTypeValue);
        try (InputStream inputStream = file.getInputStream()) {
            String storeInfo = fileHandler.upload(inputStream, fileName);
            sysFileTable.setFileName(fileName);
            sysFileTable.setStoreType(storeTypeValue);
            sysFileTable.setStoreInfo(storeInfo);
            sysFileTable.setFileSize(file.getSize());
            sysFileMapper.insert(sysFileTable);
        } catch (Exception e) {
            throw new CommonRuntimeException(e);
        }
        return SysFile.of(sysFileTable);
    }


    @Override
    public SysFile getFileInfo(String id) {
        SysFileTable sysFileTable = sysFileMapper.selectById(id);
        return SysFile.of(sysFileTable);
    }

    @Override
    public void deleteFile(String url) {

    }


    private IntegrationFileHandler getFileHandler(int storeType) {
        return this.fileHandlers.stream()
                .filter(x -> x.support(storeType))
                .findFirst()
                .orElseThrow(() -> new CommonRuntimeException("不支持的存储类型：" + storeType));

    }
}
