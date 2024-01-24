package com.dqv5.soccer.config;

import com.dqv5.soccer.common.AuthValue;
import com.dqv5.soccer.common.ConfigValue;
import com.dqv5.soccer.mapper.*;
import com.dqv5.soccer.table.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class InitDataRunner implements CommandLineRunner {
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
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysRoleAuthMapper sysRoleAuthMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysConfigMapper sysConfigMapper;


    // 角色标识 -> 角色id
    private final Map<String, String> roleMap = new HashMap<>();

    @Override
    public void run(String... args) {
        log.info("------------系统初始化开始------------");
        initConfig();
        initMenus();
        initAuths();
        initRoles();
        initDepts();
        initUsers();
        log.info("------------系统初始化结束------------");
    }

    private void initConfig() {
        long count = sysConfigMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        for (ConfigValue value : ConfigValue.values()) {
            SysConfigTable sysConfigTable = SysConfigTable.builder()
                    .configKey(value.getConfigKey())
                    .configName(value.getConfigName())
                    .configValue(value.getDefaultValue())
                    .build();
            sysConfigMapper.insert(sysConfigTable);
        }
    }

    private void initMenus() {
        long count = sysMenuMapper.selectCount(null);
        if (count > 0) {
            return;
        }

        String[][] parentMenus = {
                {"仪表盘", "anticon-dashboard"},
                {"系统管理", "anticon-setting"},
                {"个人中心", "anticon-user"},
        };
        // 菜单名称 -> 菜单id
        Map<String, String> parentMenuMap = new HashMap<>();

        for (int i = 0; i < parentMenus.length; i++) {
            String[] parentMenu = parentMenus[i];
            SysMenuTable menuTable = SysMenuTable.builder()
                    .menuName(parentMenu[0])
                    .icon(parentMenu[1])
                    .displayIndex(i + 1)
                    .build();
            sysMenuMapper.insert(menuTable);
            parentMenuMap.put(parentMenu[0], menuTable.getMenuId());
        }

        String[][] subMenus = {
                {"仪表盘", "仪表盘", "/dashboard"},
                {"系统管理", "用户管理", "/sys/user"},
                {"系统管理", "角色管理", "/sys/role"},
                {"系统管理", "菜单管理", "/sys/menu"},
                {"系统管理", "权限管理", "/sys/auth"},
                {"系统管理", "部门管理", "/sys/dept"},
                {"系统管理", "系统配置", "/sys/config"},
                {"系统管理", "系统日志", "/sys/log"},
                {"个人中心", "个人信息", "/sys/profile"},
        };

        for (int i = 0; i < subMenus.length; i++) {
            String[] subMenu = subMenus[i];
            SysMenuTable menuTable = SysMenuTable.builder()
                    .parentId(parentMenuMap.get(subMenu[0]))
                    .menuName(subMenu[1])
                    .link(subMenu[2])
                    .displayIndex(i + 1)
                    .build();
            sysMenuMapper.insert(menuTable);
        }
    }

    private void initAuths() {
        long count = sysAuthMapper.selectCount(null);
        if (count > 0) {
            return;
        }

        String[] folders = {"用户管理", "角色管理", "菜单管理", "权限管理", "部门管理", "系统配置", "系统日志"};
        // 目录名称 -> 目录id
        Map<String, String> folderIdMap = new HashMap<>();
        for (int i = 0; i < folders.length; i++) {
            String folder = folders[i];
            SysAuthFolderTable folderTable = SysAuthFolderTable.builder().authFolderName(folder).displayIndex(i + 1).build();
            sysAuthFolderMapper.insert(folderTable);
            folderIdMap.put(folder, folderTable.getAuthFolderId());
        }

        String[][] auths = {
                {"用户管理", AuthValue.SYS_USER_QUERY, "查询用户"},
                {"用户管理", AuthValue.SYS_USER_INSERT, "新增用户"},
                {"用户管理", AuthValue.SYS_USER_UPDATE, "修改用户"},
                {"用户管理", AuthValue.SYS_USER_DELETE, "删除用户"},
                {"角色管理", AuthValue.SYS_ROLE_QUERY, "查询角色"},
                {"角色管理", AuthValue.SYS_ROLE_INSERT, "新增角色"},
                {"角色管理", AuthValue.SYS_ROLE_UPDATE, "修改角色"},
                {"角色管理", AuthValue.SYS_ROLE_DELETE, "删除角色"},
                {"菜单管理", AuthValue.SYS_MENU_QUERY, "查询菜单"},
                {"菜单管理", AuthValue.SYS_MENU_INSERT, "新增菜单"},
                {"菜单管理", AuthValue.SYS_MENU_UPDATE, "修改菜单"},
                {"菜单管理", AuthValue.SYS_MENU_DELETE, "删除菜单"},
                {"权限管理", AuthValue.SYS_AUTH_QUERY, "查询权限"},
                {"权限管理", AuthValue.SYS_AUTH_INSERT, "新增权限"},
                {"权限管理", AuthValue.SYS_AUTH_UPDATE, "修改权限"},
                {"权限管理", AuthValue.SYS_AUTH_DELETE, "删除权限"},
                {"部门管理", AuthValue.SYS_DEPT_QUERY, "查询部门"},
                {"部门管理", AuthValue.SYS_DEPT_INSERT, "新增部门"},
                {"部门管理", AuthValue.SYS_DEPT_UPDATE, "修改部门"},
                {"部门管理", AuthValue.SYS_DEPT_DELETE, "删除部门"},
                {"系统配置", AuthValue.SYS_CONFIG_QUERY, "查询配置"},
                {"系统配置", AuthValue.SYS_CONFIG_SET, "保存配置"},
                {"系统日志", AuthValue.SYS_LOG_QUERY, "查询日志"},
        };
        for (int i = 0; i < auths.length; i++) {
            String[] datum = auths[i];
            sysAuthMapper.insert(SysAuthTable.builder()
                    .authFolderId(folderIdMap.get(datum[0]))
                    .authValue(datum[1])
                    .authName(datum[2])
                    .displayIndex(i + 1)
                    .build()
            );
        }
    }


    private void initRoles() {
        long count = sysRoleMapper.selectCount(null);
        if (count > 0) {
            return;
        }

        String[][] roles = {
                {"ROLE_ADMIN", "超级管理员"},
                {"ROLE_NORMAL", "普通用户"},
        };

        for (String[] role : roles) {
            SysRoleTable sysRoleTable = new SysRoleTable();
            sysRoleTable.setRoleValue(role[0]);
            sysRoleTable.setRoleName(role[1]);
            sysRoleMapper.insert(sysRoleTable);
            roleMap.put(role[0], sysRoleTable.getRoleId());
        }

        List<SysMenuTable> menus = sysMenuMapper.selectList(null);
        for (SysMenuTable menu : menus) {
            SysRoleMenuTable roleMenuTable = new SysRoleMenuTable();
            roleMenuTable.setRoleId(roleMap.get("ROLE_ADMIN"));
            roleMenuTable.setMenuId(menu.getMenuId());
            sysRoleMenuMapper.insert(roleMenuTable);
        }
        List<SysAuthTable> auths = sysAuthMapper.selectList(null);
        for (SysAuthTable auth : auths) {
            SysRoleAuthTable roleAuthTable = new SysRoleAuthTable();
            roleAuthTable.setRoleId(roleMap.get("ROLE_ADMIN"));
            roleAuthTable.setAuthId(auth.getAuthId());
            sysRoleAuthMapper.insert(roleAuthTable);
        }
    }

    private void initDepts() {
        long count = sysDeptMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        String[][] depts = {
                {"root", "根部门"}
        };
        for (String[] dept : depts) {
            String deptCode = dept[0];
            String deptName = dept[1];
            SysDeptTable sysDeptTable = new SysDeptTable();
            sysDeptTable.setDeptCode(deptCode);
            sysDeptTable.setDeptName(deptName);
            sysDeptMapper.insert(sysDeptTable);
        }
    }

    private void initUsers() {
        long count = sysUserMapper.selectCount(null);
        if (count > 0) {
            return;
        }
        String[][] users = {
                {"admin", "123456", "管理员", "ROLE_ADMIN"},
                {"tom", "111111", "汤姆", "ROLE_NORMAL"},
                {"jerry", "222222", "杰瑞", "ROLE_NORMAL"},
                {"blank", "333333", "白板", null},
        };

        for (String[] user : users) {
            String username = user[0];
            String nickName = user[2];
            String encodedPassword = passwordEncoder.encode(user[1]);
            SysUserTable sysUserTable = new SysUserTable();
            sysUserTable.setAccount(username);
            sysUserTable.setPassword(encodedPassword);
            sysUserTable.setNickName(nickName);
            sysUserMapper.insert(sysUserTable);

            String role = user[3];
            if (StringUtils.isNotBlank(role)) {
                SysUserRoleTable sysUserRoleTable = new SysUserRoleTable();
                sysUserRoleTable.setUserId(sysUserTable.getUserId());
                sysUserRoleTable.setRoleId(roleMap.get(role));
                sysUserRoleMapper.insert(sysUserRoleTable);
            }
        }

    }
}
