package com.dqv5.soccer.config;

import com.dqv5.soccer.management.entity.SysMenu;
import com.dqv5.soccer.management.entity.SysRole;
import com.dqv5.soccer.management.entity.SysUser;
import com.dqv5.soccer.management.repository.SysMenuRepository;
import com.dqv5.soccer.management.repository.SysRoleRepository;
import com.dqv5.soccer.management.repository.SysUserRepository;
import com.dqv5.soccer.pojo.RoleInfo;
import com.dqv5.soccer.pojo.UserInfo;
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
    private SysMenuRepository sysMenuRepository;
    @Resource
    private SysRoleRepository sysRoleRepository;
    @Resource
    private SysUserRepository sysUserRepository;

    @Override
    public void run(String... args) {
        log.info("------------系统初始化开始------------");
        initMenus();
        initRoles();
        initUsers();
        log.info("------------系统初始化结束------------");
    }

    private void initMenus() {
        long count = sysMenuRepository.count();
        if (count > 0) {
            return;
        }

        SysMenu dashboard = sysMenuRepository.save(SysMenu.builder().menuName("仪表盘").build());
        SysMenu p1 = new SysMenu();
        p1.setMenuId(dashboard.getMenuId());
        sysMenuRepository.save(SysMenu.builder().parentMenu(p1).menuName("仪表盘").link("/dashboard").build());

        SysMenu sys = sysMenuRepository.save(SysMenu.builder().menuName("系统管理").build());
        SysMenu p2 = new SysMenu();
        p2.setMenuId(sys.getMenuId());
        sysMenuRepository.save(SysMenu.builder().parentMenu(p2).menuName("用户管理").link("/sys/user").build());
        sysMenuRepository.save(SysMenu.builder().parentMenu(p2).menuName("角色管理").link("/sys/role").build());
        sysMenuRepository.save(SysMenu.builder().parentMenu(p2).menuName("菜单管理").link("/sys/menu").build());
        sysMenuRepository.save(SysMenu.builder().parentMenu(p2).menuName("系统日志").link("/sys/log").build());
    }

    private void initRoles() {
        long count = sysRoleRepository.count();
        if (count > 0) {
            return;
        }
        List<RoleInfo> initRoles = soccerProperties.getRoles();
        for (RoleInfo initRole : initRoles) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleValue(initRole.getRoleValue());
            sysRole.setRoleName(initRole.getRoleName());
            sysRoleRepository.save(sysRole);
        }
    }

    private void initUsers() {
        long count = sysUserRepository.count();
        if (count > 0) {
            return;
        }
        List<UserInfo> initUsers = soccerProperties.getUsers();
        for (UserInfo initUserInfo : initUsers) {
            String username = initUserInfo.getUsername();
            String nickName = initUserInfo.getNickName();
            String encodedPassword = passwordEncoder.encode(initUserInfo.getPassword());
            SysUser sysUser = new SysUser();
            sysUser.setAccount(username);
            sysUser.setPassword(encodedPassword);
            sysUser.setNickName(nickName);
            String role = initUserInfo.getRole();
            if (StringUtils.isNotBlank(role)) {
                sysRoleRepository.findByRoleValue(role).ifPresent(sysRole -> sysUser.getRoles().add(sysRole));
            }
            sysUserRepository.save(sysUser);
        }
    }

}
