package com.dqv5.soccer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan({"com.dqv5.soccer.mapper"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
