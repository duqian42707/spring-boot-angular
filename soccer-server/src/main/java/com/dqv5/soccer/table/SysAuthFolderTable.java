package com.dqv5.soccer.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_auth_folder")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAuthFolderTable extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String authFolderId;

    private String authFolderName;

    private Integer displayIndex;


}
