package com.dqv5.soccer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author duq
 * @date 2022/7/17
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "soccer")
public class SoccerProperties {
}
