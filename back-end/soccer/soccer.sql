/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.102.171
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : 192.168.102.171:3306
 Source Schema         : soccer

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 07/03/2019 15:12:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_file
-- ----------------------------
DROP TABLE IF EXISTS `basic_file`;
CREATE TABLE `basic_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file_hash` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `file_key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `last_modified` datetime DEFAULT NULL,
  `mime_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `upload_from` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for basic_user
-- ----------------------------
DROP TABLE IF EXISTS `basic_user`;
CREATE TABLE `basic_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `avatar_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_password_reset_time` datetime DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `mod_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc4e23ymy8js3t97mv71idjjhh` (`mod_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of basic_user
-- ----------------------------
BEGIN;
INSERT INTO `basic_user` VALUES (1, 'admin', NULL, NULL, NULL, NULL, '$2a$10$dFfdqjrar3ri3TLANHP3SeMMUjNTO8KlXg67kDT9aeFIX1doPHLk6', NULL, NULL, '管理员', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for basic_user_role
-- ----------------------------
DROP TABLE IF EXISTS `basic_user_role`;
CREATE TABLE `basic_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of basic_user_role
-- ----------------------------
BEGIN;
INSERT INTO `basic_user_role` VALUES (1, 1, 1);
INSERT INTO `basic_user_role` VALUES (2, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `display` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `enable` char(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `type` char(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_func_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_func_right`;
CREATE TABLE `sys_func_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `func_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `perm_value` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKojjk9tkjwsg2mb7isqvlnqot2` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4xc8y0nxiniot9q24tkumoj4b` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `mod_user_id` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `level_no` int(11) DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `expand` char(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdqnac8hs89i4rxhotct6er9lr` (`create_user_id`),
  KEY `FKad2337ra42mi99p00ctyefac8` (`mod_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
BEGIN;
INSERT INTO `sys_module` VALUES (1, NULL, NULL, '系统管理', 10, NULL, NULL, NULL, NULL, NULL, NULL, 'anticon anticon-setting', NULL);
INSERT INTO `sys_module` VALUES (2, NULL, NULL, '用户管理', 1, NULL, NULL, NULL, '/sys/user', 1, NULL, 'anticon anticon-team', NULL);
INSERT INTO `sys_module` VALUES (4, NULL, NULL, '角色管理', 1, NULL, NULL, NULL, '/sys/role', 3, NULL, 'anticon anticon-form', NULL);
INSERT INTO `sys_module` VALUES (10, NULL, NULL, '所有菜单', NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `sys_module` VALUES (12, NULL, NULL, '模块管理', 1, NULL, NULL, NULL, '/sys/module', 2, NULL, 'anticon anticon-bars', NULL);
INSERT INTO `sys_module` VALUES (21, NULL, NULL, '字典管理', 1, NULL, NULL, NULL, '/sys/dict', 4, NULL, NULL, NULL);
INSERT INTO `sys_module` VALUES (26, NULL, NULL, '测试模块', 10, NULL, NULL, NULL, NULL, 2, NULL, 'anticon anticon-setting', NULL);
INSERT INTO `sys_module` VALUES (27, NULL, NULL, '测试1', 26, NULL, NULL, NULL, '/test/test1', 1, NULL, NULL, NULL);
INSERT INTO `sys_module` VALUES (28, NULL, NULL, '测试2', 26, NULL, NULL, NULL, '/test/test2', 2, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `last_modified_time` datetime DEFAULT NULL,
  `role_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_user_id` int(11) DEFAULT NULL,
  `mod_user_id` int(11) DEFAULT NULL,
  `protect` char(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '保留角色：1是 ',
  PRIMARY KEY (`id`),
  KEY `FKmq56blie4bjdlb0gqm3nfuote` (`create_user_id`),
  KEY `FKoc6pejs2by1vp3041sd045rek` (`mod_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '2018-09-05 16:45:30', '2019-03-07 15:11:29', '普通用户', NULL, NULL, '');
INSERT INTO `sys_role` VALUES (2, '2018-09-09 14:03:34', '2019-03-07 15:11:32', '管理员', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_value` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjqcbph1hwbmvesk9jyg6vqcw6` (`module_id`),
  KEY `FKejwxxhh6d84nq0fw06chviiqu` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_module` VALUES (81, NULL, 27, 1);
INSERT INTO `sys_role_module` VALUES (82, NULL, 28, 1);
INSERT INTO `sys_role_module` VALUES (83, NULL, 2, 2);
INSERT INTO `sys_role_module` VALUES (84, NULL, 4, 2);
INSERT INTO `sys_role_module` VALUES (85, NULL, 12, 2);
INSERT INTO `sys_role_module` VALUES (86, NULL, 21, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
