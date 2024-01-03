package com.dqv5.soccer.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duq
 * @date 2024/1/3
 */
@AllArgsConstructor
@Getter
public class AuthValue {

    public static final String SYS_USER_QUERY = "sys_user_query";
    public static final String SYS_USER_INSERT = "sys_user_insert";
    public static final String SYS_USER_UPDATE = "sys_user_update";
    public static final String SYS_USER_DELETE = "sys_user_delete";

    public static final String SYS_ROLE_QUERY = "sys_role_query";
    public static final String SYS_ROLE_INSERT = "sys_role_insert";
    public static final String SYS_ROLE_UPDATE = "sys_role_update";
    public static final String SYS_ROLE_DELETE = "sys_role_delete";

    public static final String SYS_MENU_QUERY = "sys_menu_query";
    public static final String SYS_MENU_INSERT = "sys_menu_insert";
    public static final String SYS_MENU_UPDATE = "sys_menu_update";
    public static final String SYS_MENU_DELETE = "sys_menu_delete";

    public static final String SYS_AUTH_QUERY = "sys_auth_query";
    public static final String SYS_AUTH_INSERT = "sys_auth_insert";
    public static final String SYS_AUTH_UPDATE = "sys_auth_update";
    public static final String SYS_AUTH_DELETE = "sys_auth_delete";

    public static final String SYS_LOG_QUERY = "sys_log_query";
}
