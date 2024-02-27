package com.dqv5.soccer.utils;


/**
 * @author admin
 * @date 2018/8/30
 */
public class DqFileUtils {
    /**
     * 视频文件后缀
     */
    public static final String[] VIDEO_FILE_EXTENSIONS = {"mp4", "wmv", "mov", "avi", "flv", "rm", "rmvb", "mpg", "mkv"};
    /**
     * 音频文件后缀
     */
    public static final String[] AUDIO_FILE_EXTENSIONS = {"mp3", "wav", "ogg", "wma"};
    /**
     * 图片文件后缀
     */
    public static final String[] IMAGE_FILE_EXTENSIONS = {"jpg", "jpeg", "bmp", "png", "gif"};


    /**
     * 判断是否为视频格式
     */
    public static boolean isVideo(String extension) {
        for (String s : VIDEO_FILE_EXTENSIONS) {
            if (s.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为音频格式
     */
    public static boolean isAudio(String extension) {
        for (String s : AUDIO_FILE_EXTENSIONS) {
            if (s.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为图片格式
     */
    public static boolean isPic(String extension) {
        for (String s : IMAGE_FILE_EXTENSIONS) {
            if (s.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }


}
