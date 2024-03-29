package com.dqv5.soccer.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author duq
 * @date 2024/1/3
 */
@AllArgsConstructor
@Getter
public enum ConfigValue {
    SYS_NAME("sys_name", "系统名称", "Soccer"),
    SYS_DESC("sys_desc", "系统描述", "Springboot + Angular 开发框架"),
    SYS_LOGO_BIG("sys_logo_big", "系统Logo（大）", "https://image.dqv5.com/public/soccer.png"),
    SYS_LOGO_SMALL("sys_logo_small", "系统Logo（小）", "https://image.dqv5.com/public/soccer.png"),
    ;

    private final String configKey;
    private final String configName;
    private final String defaultValue;

}
