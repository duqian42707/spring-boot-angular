package com.dqv5.soccer.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author duq
 * @date 2024/2/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_file")
public class SysFileTable extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private Integer duration;
    private String sha256;
    private Integer storeType;
    private String storeInfo;
    private String remark;
}
