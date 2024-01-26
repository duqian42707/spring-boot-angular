package com.dqv5.soccer.config;

import com.dqv5.soccer.service.InitDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class InitDataRunner implements CommandLineRunner {

    @Resource
    private InitDataService initDataService;

    @Override
    public void run(String... args) {
        initDataService.initData(false);
    }


}
