package com.dqv5.soccer.config;

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
@ConfigurationProperties(prefix = "soccer.amazon-s3")
public class AmazonS3Properties {
    private boolean enable;
    private String endpoint;
    private String bucketName;
    private String accessKey;
    private String secretKey;
}
