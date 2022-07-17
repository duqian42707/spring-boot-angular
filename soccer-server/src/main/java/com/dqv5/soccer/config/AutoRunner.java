package com.dqv5.soccer.config;

import com.dqv5.soccer.pojo.UserInfo;
import com.dqv5.soccer.pojo.entity.BaseRole;
import com.dqv5.soccer.pojo.entity.BaseUser;
import com.dqv5.soccer.repository.BaseRoleRepository;
import com.dqv5.soccer.repository.BaseUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class AutoRunner implements CommandLineRunner {
    @Resource
    private SoccerProperties soccerProperties;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private BaseRoleRepository baseRoleRepository;
    @Resource
    private BaseUserRepository baseUserRepository;

    @Override
    public void run(String... args) {
        log.info("------------系统初始化开始------------");
        initUsers();
        log.info("------------系统初始化结束------------");
    }

    private void initUsers() {
        long count = baseUserRepository.count();
        if (count > 0) {
            return;
        }
        List<UserInfo> initUsers = soccerProperties.getUsers();
        for (UserInfo initUserInfo : initUsers) {
            String username = initUserInfo.getUsername();
            String encodedPassword = passwordEncoder.encode(initUserInfo.getPassword());
            BaseUser baseUser = new BaseUser();
            baseUser.setAccount(username);
            baseUser.setPassword(encodedPassword);
            String role = initUserInfo.getRole();
            if (StringUtils.isNotBlank(role)) {
                BaseRole baseRole = baseRoleRepository.findByRoleValue(role).orElse(new BaseRole());
                baseRole.setRoleValue(role);
                baseRole.setRoleName(role);
                baseRoleRepository.save(baseRole);
                baseUser.getRoles().add(baseRole);
            }
            baseUserRepository.save(baseUser);
        }
    }

}
