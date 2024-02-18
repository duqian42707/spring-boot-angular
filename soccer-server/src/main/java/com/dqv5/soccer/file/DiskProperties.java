package com.dqv5.soccer.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author duq
 * @date 2024/1/23
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "soccer.file-store.disk")
public class DiskProperties {
    private boolean enable;
    private String root;
}
