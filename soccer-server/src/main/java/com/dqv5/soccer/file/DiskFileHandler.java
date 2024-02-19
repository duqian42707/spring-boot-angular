package com.dqv5.soccer.file;

import com.dqv5.soccer.exception.CommonRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author duq
 * @date 2024/2/17
 */
@Component
@Slf4j
public class DiskFileHandler implements IntegrationFileHandler {
    @Resource
    private DiskProperties diskProperties;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public String upload(InputStream inputStream, String fileName) {
        String rootDir = diskProperties.getRoot();
        String relativeFilePath = this.generateRelativeFilePath(fileName);
        File file = new File(rootDir + "/" + relativeFilePath);
        File parentFolder = file.getParentFile();
        if (!parentFolder.exists() && !parentFolder.mkdirs()) {
            throw new CommonRuntimeException("目录创建失败：" + parentFolder.getAbsolutePath());
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return relativeFilePath;
    }

    @Override
    public InputStream getInputStream(String storeInfo) {
        String rootDir = diskProperties.getRoot();
        File file = new File(rootDir + "/" + storeInfo);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new CommonRuntimeException(e);
        }
    }

    @Override
    public void delete(String storeInfo) {

    }

    @Override
    public boolean support(int storeType) {
        return FileStoreType.Disk.getValue() == storeType;
    }

    /**
     * 根据文件名，生成新文件路径（相对路径），格式："yyyy/MM/uuid.suf"
     * AmazonS3会根据斜杠创建子目录
     *
     * @param originFileName
     * @return
     */
    private String generateRelativeFilePath(String originFileName) {
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
