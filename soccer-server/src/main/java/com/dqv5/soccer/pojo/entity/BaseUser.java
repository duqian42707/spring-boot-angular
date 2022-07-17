package com.dqv5.soccer.pojo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author duq
 * @date 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "base_user")
@EntityListeners(AuditingEntityListener.class)
public class BaseUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    private String userId;
    private String account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String phone;
    private String email;
    private String status;
    private String openid;
    private Date lastLoginTime;
    private Date lastPasswordResetTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "base_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<BaseRole> roles = new HashSet<>();
}
