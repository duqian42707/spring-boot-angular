package com.dqv5.soccer.service.impl;

import cn.hutool.core.io.FileUtil;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.file.FileStoreType;
import com.dqv5.soccer.file.IntegrationFileHandler;
import com.dqv5.soccer.mapper.SysFileMapper;
import com.dqv5.soccer.pojo.SysFile;
import com.dqv5.soccer.service.SysFileService;
import com.dqv5.soccer.table.SysFileTable;
import com.dqv5.soccer.utils.MediaFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Tika tika = new Tika();

    @Override
    public SysFile uploadFile(MultipartFile file) {
        SysFileTable sysFileTable = new SysFileTable();
        String fileName = file.getOriginalFilename();

        String sha256;
        try (InputStream inputStream = file.getInputStream()) {
            sha256 = DigestUtils.sha256Hex(inputStream);
        } catch (IOException e) {
            throw new CommonRuntimeException(e);
        }

        int storeType = 0;
        String storeInfo = null;
        Integer duration = null;
        boolean exist = false;

        Map<String, Object> param = new HashMap<>();
        param.put("sha256", sha256);
        List<SysFileTable> existFiles = sysFileMapper.selectByMap(param);
        for (SysFileTable existFile : existFiles) {
            Integer existStoreType = existFile.getStoreType();
            String existStoreInfo = existFile.getStoreInfo();
            IntegrationFileHandler existFileHandler = this.getFileHandler(existStoreType);

            if (existFileHandler.checkFile(existStoreInfo, sha256)) {
                storeType = existStoreType;
                storeInfo = existStoreInfo;
                duration = existFile.getDuration();
                exist = true;
                break;
            }
        }

        if (!exist) {
            storeType = FileStoreType.fromName(defaultStoreType).getValue();
            IntegrationFileHandler fileHandler = this.getFileHandler(storeType);
            try (InputStream inputStream = file.getInputStream()) {
                storeInfo = fileHandler.upload(inputStream, fileName);
            } catch (Exception e) {
                throw new CommonRuntimeException(e);
            }
            try (InputStream inputStream = file.getInputStream()) {
                duration = MediaFileUtils.getDuration(inputStream, fileName);
            } catch (IOException e) {
                throw new CommonRuntimeException(e);
            }
        }
        String extName = FileUtil.extName(fileName);
        sysFileTable.setFileName(fileName);
        sysFileTable.setFileType(extName);
        sysFileTable.setStoreType(storeType);
        sysFileTable.setStoreInfo(storeInfo);
        sysFileTable.setFileSize(file.getSize());
        sysFileTable.setSha256(sha256);
        sysFileTable.setDuration(duration);
        sysFileMapper.insert(sysFileTable);
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
        String mimeType = tika.detect(fileName);

        try (InputStream inputStream = fileHandler.getInputStream(sysFileTable.getStoreInfo());
             ServletOutputStream outputStream = response.getOutputStream();) {
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            String dispositionType = StringUtils.isBlank(dl) ? "inline" : "attachment";
            ContentDisposition disposition = ContentDisposition.builder(dispositionType).filename(encodedFileName).build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(disposition);
            headers.setContentType(MediaType.parseMediaType(mimeType));
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
