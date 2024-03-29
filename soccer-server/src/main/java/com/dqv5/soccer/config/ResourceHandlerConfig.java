package com.dqv5.soccer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cacheControl = CacheControl.maxAge(Duration.ofDays(365)).cachePublic();
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/static/web/").setCacheControl(cacheControl);
        registry.addResourceHandler("/swagger-ui/").addResourceLocations("classpath:/META-INF/resources/");
    }
}
