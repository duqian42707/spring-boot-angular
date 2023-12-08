package com.dqv5.soccer.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.management.mapper.SysMenuMapper;
import com.dqv5.soccer.management.mapper.SysRoleMapper;
import com.dqv5.soccer.management.mapper.SysUserMapper;
import com.dqv5.soccer.management.mapper.SysUserRoleMapper;
import com.dqv5.soccer.management.table.SysMenu;
import com.dqv5.soccer.management.table.SysRole;
import com.dqv5.soccer.management.table.SysUser;
import com.dqv5.soccer.management.table.SysUserRole;
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
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void run(String... args) {
        log.info("------------系统初始化开始------------");
        initMenus();
        initRoles();
        initUsers();
        log.info("------------系统初始化结束------------");
    }

    private void initMenus() {
        long count = sysMenuMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        SysMenu dashboard = SysMenu.builder().menuName("仪表盘").build();
        sysMenuMapper.insert(dashboard);
        sysMenuMapper.insert(SysMenu.builder().parentId(dashboard.getMenuId()).menuName("仪表盘").link("/dashboard").build());
        SysMenu sys = SysMenu.builder().menuName("系统管理").build();
        sysMenuMapper.insert(sys);
        sysMenuMapper.insert(SysMenu.builder().parentId(sys.getMenuId()).menuName("用户管理").link("/sys/user").build());
        sysMenuMapper.insert(SysMenu.builder().parentId(sys.getMenuId()).menuName("角色管理").link("/sys/role").build());
        sysMenuMapper.insert(SysMenu.builder().parentId(sys.getMenuId()).menuName("菜单管理").link("/sys/menu").build());
        sysMenuMapper.insert(SysMenu.builder().parentId(sys.getMenuId()).menuName("系统日志").link("/sys/log").build());
    }

    private void initRoles() {
        long count = sysRoleMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        List<RoleInfo> initRoles = soccerProperties.getRoles();
        for (RoleInfo initRole : initRoles) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleValue(initRole.getRoleValue());
            sysRole.setRoleName(initRole.getRoleName());
            sysRoleMapper.insert(sysRole);
        }
    }

    private void initUsers() {
        long count = sysUserMapper.selectCount(null);
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
            sysUserMapper.insert(sysUser);
            String role = initUserInfo.getRole();
            if (StringUtils.isNotBlank(role)) {
                QueryWrapper<SysRole> queryWrapper = Wrappers.query(SysRole.class).eq("role_value", role);
                SysRole sysRole = sysRoleMapper.selectOne(queryWrapper);
                if (sysRole != null) {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUser.getUserId());
                    sysUserRole.setRoleId(sysRole.getRoleId());
                    sysUserRoleMapper.insert(sysUserRole);
                }
            }
        }
    }

}
