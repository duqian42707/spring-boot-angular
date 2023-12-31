package com.dqv5.soccer.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dqv5.soccer.mapper.*;
import com.dqv5.soccer.table.*;
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
    private SysAuthFolderMapper sysAuthFolderMapper;
    @Resource
    private SysAuthMapper sysAuthMapper;
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
        initAuths();
        initRoles();
        initUsers();
        log.info("------------系统初始化结束------------");
    }


    private void initMenus() {
        long count = sysMenuMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        SysMenuTable dashboard = SysMenuTable.builder().menuName("仪表盘").build();
        sysMenuMapper.insert(dashboard);
        SysMenuTable dashboard2 = SysMenuTable.builder().parentId(dashboard.getMenuId()).menuName("仪表盘").link("/dashboard").build();
        sysMenuMapper.insert(dashboard2);

        SysMenuTable sys = SysMenuTable.builder().menuName("系统管理").build();
        sysMenuMapper.insert(sys);
        SysMenuTable user = SysMenuTable.builder().parentId(sys.getMenuId()).menuName("用户管理").link("/sys/user").build();
        SysMenuTable role = SysMenuTable.builder().parentId(sys.getMenuId()).menuName("角色管理").link("/sys/role").build();
        SysMenuTable menu = SysMenuTable.builder().parentId(sys.getMenuId()).menuName("菜单管理").link("/sys/menu").build();
        SysMenuTable log = SysMenuTable.builder().parentId(sys.getMenuId()).menuName("系统日志").link("/sys/log").build();
        sysMenuMapper.insert(user);
        sysMenuMapper.insert(role);
        sysMenuMapper.insert(menu);
        sysMenuMapper.insert(log);


    }

    private void initAuths() {
        long count = sysAuthMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        SysAuthFolderTable user = SysAuthFolderTable.builder().authFolderName("用户管理").build();
        sysAuthFolderMapper.insert(user);
        sysAuthMapper.insert(SysAuthTable.builder().authFolderId(user.getAuthFolderId()).authValue("sys_user_query").authName("查询用户").build());
        sysAuthMapper.insert(SysAuthTable.builder().authFolderId(user.getAuthFolderId()).authValue("sys_user_insert").authName("新增用户").build());
        sysAuthMapper.insert(SysAuthTable.builder().authFolderId(user.getAuthFolderId()).authValue("sys_user_update").authName("修改用户").build());
        sysAuthMapper.insert(SysAuthTable.builder().authFolderId(user.getAuthFolderId()).authValue("sys_user_delete").authName("删除用户").build());
    }


    private void initRoles() {
        long count = sysRoleMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        List<RoleInfo> initRoles = soccerProperties.getRoles();
        for (RoleInfo initRole : initRoles) {
            SysRoleTable sysRoleTable = new SysRoleTable();
            sysRoleTable.setRoleValue(initRole.getRoleValue());
            sysRoleTable.setRoleName(initRole.getRoleName());
            sysRoleMapper.insert(sysRoleTable);
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
            SysUserTable sysUserTable = new SysUserTable();
            sysUserTable.setAccount(username);
            sysUserTable.setPassword(encodedPassword);
            sysUserTable.setNickName(nickName);
            sysUserMapper.insert(sysUserTable);
            String role = initUserInfo.getRole();
            if (StringUtils.isNotBlank(role)) {
                QueryWrapper<SysRoleTable> queryWrapper = Wrappers.query(SysRoleTable.class).eq("role_value", role);
                SysRoleTable sysRoleTable = sysRoleMapper.selectOne(queryWrapper);
                if (sysRoleTable != null) {
                    SysUserRoleTable sysUserRoleTable = new SysUserRoleTable();
                    sysUserRoleTable.setUserId(sysUserTable.getUserId());
                    sysUserRoleTable.setRoleId(sysRoleTable.getRoleId());
                    sysUserRoleMapper.insert(sysUserRoleTable);
                }
            }
        }
    }

}
