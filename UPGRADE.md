# 升级记录

## 升级 SpringBoot 3.5.x

- 修改包名 javax.xxx -> jakarta.xxx
- Spring Security 6.x 相关改动
  - `@EnableGlobalMethodSecurity` 改为 `@EnableMethodSecurity`
  - `SecurityFilterChain` 配置方式修改
- knife4j 改为 OpenAPI3规范版本，注解有变化
