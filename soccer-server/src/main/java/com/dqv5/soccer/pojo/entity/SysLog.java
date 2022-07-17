package com.dqv5.soccer.pojo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author admin
 * @date 2022/7/17
 */
@Data
@Slf4j
public class SysLog implements Serializable {
    private Integer id;
    private String detail;
    private String ip;
    private Date operateTime;

}
