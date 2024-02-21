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
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
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
    public void downloadFile(String id, String dl, HttpServletResponse response) {
        SysFileTable sysFileTable = sysFileMapper.selectById(id);
        String fileName = sysFileTable.getFileName();
        IntegrationFileHandler fileHandler = getFileHandler(sysFileTable.getStoreType());

        try (InputStream inputStream = fileHandler.getInputStream(sysFileTable.getStoreInfo());
             ServletOutputStream outputStream = response.getOutputStream();) {
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            String dispositionType = StringUtils.isBlank(dl) ? "inline" : "attachment";
            ContentDisposition disposition = ContentDisposition.builder(dispositionType).filename(encodedFileName).build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(disposition);
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value != null && !value.isEmpty()) {
                    response.setHeader(key, value.get(0));
                }
            }
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    @Override
    public void deleteFile(String id) {
        SysFileTable sysFileTable = sysFileMapper.selectById(id);
        IntegrationFileHandler fileHandler = getFileHandler(sysFileTable.getStoreType());
        fileHandler.delete(sysFileTable.getStoreInfo());
        sysFileMapper.deleteById(id);
    }


    private IntegrationFileHandler getFileHandler(int storeType) {
        return this.fileHandlers.stream()
                .filter(x -> x.support(storeType))
                .findFirst()
                .orElseThrow(() -> new CommonRuntimeException("不支持的存储类型：" + storeType));

    }
}
