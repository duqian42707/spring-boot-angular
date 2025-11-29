package com.dqv5.soccer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@MapperScan({"com.dqv5.soccer.mapper"})
@EnableMethodSecurity
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
