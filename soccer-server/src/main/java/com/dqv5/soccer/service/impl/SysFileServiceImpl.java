package com.dqv5.soccer.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dqv5.soccer.config.AmazonS3Properties;
import com.dqv5.soccer.exception.CommonRuntimeException;
import com.dqv5.soccer.mapper.SysFileMapper;
import com.dqv5.soccer.pojo.FileUploadDto;
import com.dqv5.soccer.pojo.SysFile;
import com.dqv5.soccer.service.SysFileService;
import com.dqv5.soccer.table.SysFileTable;
import com.dqv5.soccer.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author duqian
 * @date 2024/1/23
 */
@Service
@Slf4j
public class SysFileServiceImpl implements SysFileService {
    @Resource
    private AmazonS3Properties properties;
    @Resource
    private SysFileMapper sysFileMapper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public SysFile uploadFile(MultipartFile file) {
        AmazonS3 amazonS3 = SpringUtils.getBean(AmazonS3.class);
        if (amazonS3 == null) {
            throw new CommonRuntimeException("未配置s3！");
        }
        SysFileTable sysFileTable = new SysFileTable();
        String bucketName = properties.getBucketName();
        String fileName = file.getOriginalFilename();
        String key = this.generateFileKey(fileName);
        sysFileTable.setFileName(fileName);
        sysFileTable.setStoreType(2);
        sysFileTable.setStoreInfo(key);
        sysFileTable.setFileSize(file.getSize());
        // todo 其他属性...
        try (InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(bucketName, key, inputStream, new ObjectMetadata());
        } catch (Exception e) {
            log.error("上传异常", e);
            throw new CommonRuntimeException(e.getMessage());
        }
        sysFileMapper.insert(sysFileTable);
        return SysFile.of(sysFileTable);
    }


    @Override
    public SysFile getFileInfo(String id) {
        return null;
    }

    @Override
    public void deleteFile(String url) {

    }

    /**
     * 根据文件名，生成新文件路径，格式："yyyy/MM/uuid.suf"
     * AmazonS3会根据斜杠创建子目录
     *
     * @param originFileName
     * @return
     */
    private String generateFileKey(String originFileName) {
        String newName = UUID.randomUUID().toString().replace("-", "");
        int index = originFileName.lastIndexOf(".");
        if (index > -1) {
            newName += originFileName.substring(index);
        }
        String yearMonth = dateTimeFormatter.format(LocalDate.now());
        String year = yearMonth.substring(0, 4);
        String month = yearMonth.substring(4);
        return year + "/" + month + "/" + newName;
    }
}
