package com.dqv5.soccer.utils;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.info.MultimediaInfo;

import java.io.*;
import java.nio.file.Files;

/**
 * 音视频处理工具类
 *
 * @author duqian
 * @date 2020/8/3
 */
@Slf4j
public class MediaFileUtils {

    /**
     * 获取音视频长度，单位：秒
     *
     * @param inputStream
     * @return
     */
    public static Integer getDuration(InputStream inputStream, String fileName) {
        String mainName = FileUtil.mainName(fileName);
        String extName = FileUtil.extName(fileName);
        if (!DqFileUtils.isVideo(extName) && !DqFileUtils.isAudio(extName)) {
            return null;
        }
        File temp = null;
        OutputStream outputStream = null;
        try {
            temp = File.createTempFile(mainName, "." + extName);
            outputStream = Files.newOutputStream(temp.toPath());
            IOUtils.copy(inputStream, outputStream);
            return getDuration(temp);
        } catch (IOException e) {
            return null;
        } finally {
            IOUtils.closeQuietly(outputStream);
            FileUtils.deleteQuietly(temp);
        }
    }

    /**
     * 获取音视频长度，单位：秒
     *
     * @param file
     * @return
     */
    public static Integer getDuration(File file) {
        String fileName = file.getName();
        String extName = FileUtil.extName(fileName);
        if (!DqFileUtils.isVideo(extName) && !DqFileUtils.isAudio(extName)) {
            return null;
        }
        MultimediaObject multimediaObject = new MultimediaObject(file);
        try {
            MultimediaInfo m = multimediaObject.getInfo();
            long second = m.getDuration() / 1000;
            return (int) second;
        } catch (Exception e) {
            log.error("获取音视频长度异常", e);
            return null;
        }
    }
}
