package com.dqv5.soccer.management.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author admin
 * @date 2022/7/17
 */
@Data
@TableName("sys_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_UUID)
    private String logId;
    private String userId;
    private String username;
    private String nickName;
    private String ip;
    private String address;
    private String className;
    private String methodName;
    private String methodDesc;
    private String args;
    private String requestUrl;
    private String requestType;
    private Integer status;
    private String errorInfo;
    private Long runTime;
    private Date createdDate;
}
