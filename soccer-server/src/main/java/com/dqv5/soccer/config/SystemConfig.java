package com.dqv5.soccer.config;

import com.dqv5.soccer.security.AuthUser;
import com.dqv5.soccer.utils.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author duq
 * @date 2022/7/17
 */
@Configuration
public class SystemConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityUtils.getCurrentUserDetail())
                .map((i) -> i.getUserId() + "~~~" + i.getNickName());
    }
}
