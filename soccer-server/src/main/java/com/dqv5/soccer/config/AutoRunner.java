package com.dqv5.soccer.config;

import com.dqv5.soccer.pojo.UserInfo;
import com.dqv5.soccer.management.entity.SysRole;
import com.dqv5.soccer.management.entity.SysUser;
import com.dqv5.soccer.management.repository.SysRoleRepository;
import com.dqv5.soccer.management.repository.SysUserRepository;
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
    private SysRoleRepository sysRoleRepository;
    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public void run(String... args) {
        log.info("------------系统初始化开始------------");
        initUsers();
        log.info("------------系统初始化结束------------");
    }

    private void initUsers() {
        long count = sysUserRepository.count();
        if (count > 0) {
            return;
        }
        List<UserInfo> initUsers = soccerProperties.getUsers();
        for (UserInfo initUserInfo : initUsers) {
            String username = initUserInfo.getUsername();
            String encodedPassword = passwordEncoder.encode(initUserInfo.getPassword());
            SysUser sysUser = new SysUser();
            sysUser.setAccount(username);
            sysUser.setPassword(encodedPassword);
            String role = initUserInfo.getRole();
            if (StringUtils.isNotBlank(role)) {
                SysRole sysRole = sysRoleRepository.findByRoleValue(role).orElse(new SysRole());
                sysRole.setRoleValue(role);
                sysRole.setRoleName(role);
                sysRoleRepository.save(sysRole);
                sysUser.getRoles().add(sysRole);
            }
            sysUserRepository.save(sysUser);
        }
    }

}
