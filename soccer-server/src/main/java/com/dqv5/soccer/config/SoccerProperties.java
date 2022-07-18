package com.dqv5.soccer.config;

import com.dqv5.soccer.pojo.RoleInfo;
import com.dqv5.soccer.pojo.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author duq
 * @date 2022/7/17
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "soccer")
public class SoccerProperties {
    private List<UserInfo> users;
    private List<RoleInfo> roles;
}
