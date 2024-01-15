package com.dqv5.soccer.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_config")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysConfigTable extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String configKey;

    private String configValue;

    private String configName;



}
