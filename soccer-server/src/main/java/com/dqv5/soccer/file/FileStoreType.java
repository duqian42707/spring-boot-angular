package com.dqv5.soccer.file;

import com.dqv5.soccer.exception.CommonRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duq
 * @date 2024/2/17
 */
@Getter
@AllArgsConstructor
public enum FileStoreType {
    Disk(1, "disk"),
    AmazonS3(2, "s3"),
    ;

    private final int value;
    private final String name;

    public static FileStoreType fromName(String name) {
        for (FileStoreType value : FileStoreType.values()) {
            if (value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new CommonRuntimeException("参数异常：" + name);
    }

}
