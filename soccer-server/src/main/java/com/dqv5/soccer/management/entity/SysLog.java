package com.dqv5.soccer.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author admin
 * @date 2022/7/17
 */
@Data
@Entity
@Table(name = "sys_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String logId;
    private String userId;
    private String username;
    private String nickName;
    private String ip;
    private String address;
    private String className;
    private String methodName;
    private String methodDesc;
    @Lob
    private String args;
    @Column(length = 2000)
    private String requestUrl;
    private String requestType;
    private Integer status;
    @Lob
    private String errorInfo;
    private Long runTime;
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Date createdDate;
}
