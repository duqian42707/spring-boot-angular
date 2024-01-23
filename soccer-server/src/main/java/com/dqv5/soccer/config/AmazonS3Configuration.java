package com.dqv5.soccer.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 使用<code>@ConditionalOnProperty</code>注解，满足一定条件时才加载配置类或bean，
 * 用法：https://blog.csdn.net/qq_33790670/article/details/108800037
 *
 * @author duqian
 * @date 2021/05/28
 */
@Configuration
@ConditionalOnProperty(name = "soccer.amazons3.enable", havingValue = "true")
public class AmazonS3Configuration {
    @Resource
    private AmazonS3Properties properties;

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxConnections(500);
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(properties.getEndpoint(), ""))
                .withClientConfiguration(clientConfiguration)
                // 通过域名访问时，bucketName不作为子域名而是作为下一级路径
                .withPathStyleAccessEnabled(true)
                .build();
        if (!s3.doesBucketExistV2(properties.getBucketName())) {
            s3.createBucket(properties.getBucketName());
        }
        return s3;
    }


}
