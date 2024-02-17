package com.dqv5.soccer.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dqv5.soccer.exception.CommonRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author duq
 * @date 2024/2/17
 */
@Component
@Slf4j
@ConditionalOnProperty(name = "soccer.file-store.s3.enable", havingValue = "true")
public class AmazonS3FileHandler implements IntegrationFileHandler {
    @Resource
    private AmazonS3 amazonS3;
    @Resource
    private AmazonS3Properties properties;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public String upload(InputStream inputStream, String fileName) {
        String bucketName = properties.getBucketName();
        String key = this.generateFileKey(fileName);
        try {
            amazonS3.putObject(bucketName, key, inputStream, new ObjectMetadata());
        } catch (Exception e) {
            log.error("上传异常", e);
            throw new CommonRuntimeException(e.getMessage());
        }
        return key;
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
        return FileStoreType.AmazonS3.getValue() == storeType;
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
