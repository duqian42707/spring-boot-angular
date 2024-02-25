package com.dqv5.soccer.pojo;

import com.dqv5.soccer.table.SysFileTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author duq
 * @date 2024/2/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysFile extends AbstractBaseVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private Integer duration;
    private String sha256;
    private Integer storeType;
    private String storeInfo;
    private String remark;
    private String url;

    public static SysFile of(SysFileTable table) {
        SysFile sysFile = new SysFile();
        BeanUtils.copyProperties(table, sysFile);
        sysFile.setUrl("/api/file/file/" + table.getFileId());
        return sysFile;
    }

    public SysFileTable toTable() {
        SysFileTable table = new SysFileTable();
        BeanUtils.copyProperties(this, table);
        return table;
    }

}
