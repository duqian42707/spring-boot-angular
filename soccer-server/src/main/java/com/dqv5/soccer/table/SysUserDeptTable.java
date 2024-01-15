package com.dqv5.soccer.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_dept")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDeptTable extends AbstractBaseTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String deptId;


}
