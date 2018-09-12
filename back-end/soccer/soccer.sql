/*
 Navicat Premium Data Transfer

 Source Server         : dqv5.com
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : dqv5.com:3306
 Source Schema         : soccer

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/09/2018 20:39:49
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
  KEY `FKc4e23ymy8js3t97mv71idjjhh` (`mod_user_id`),
  CONSTRAINT `FKc4e23ymy8js3t97mv71idjjhh` FOREIGN KEY (`mod_user_id`) REFERENCES `basic_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for basic_user_role
-- ----------------------------
DROP TABLE IF EXISTS `basic_user_role`;
CREATE TABLE `basic_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
  KEY `FKojjk9tkjwsg2mb7isqvlnqot2` (`module_id`),
  CONSTRAINT `FKojjk9tkjwsg2mb7isqvlnqot2` FOREIGN KEY (`module_id`) REFERENCES `sys_module` (`id`)
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
  KEY `FK4xc8y0nxiniot9q24tkumoj4b` (`user_id`),
  CONSTRAINT `FK4xc8y0nxiniot9q24tkumoj4b` FOREIGN KEY (`user_id`) REFERENCES `basic_user` (`id`)
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
  KEY `FKad2337ra42mi99p00ctyefac8` (`mod_user_id`),
  CONSTRAINT `FKad2337ra42mi99p00ctyefac8` FOREIGN KEY (`mod_user_id`) REFERENCES `basic_user` (`id`),
  CONSTRAINT `FKdqnac8hs89i4rxhotct6er9lr` FOREIGN KEY (`create_user_id`) REFERENCES `basic_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
  KEY `FKoc6pejs2by1vp3041sd045rek` (`mod_user_id`),
  CONSTRAINT `FKmq56blie4bjdlb0gqm3nfuote` FOREIGN KEY (`create_user_id`) REFERENCES `basic_user` (`id`),
  CONSTRAINT `FKoc6pejs2by1vp3041sd045rek` FOREIGN KEY (`mod_user_id`) REFERENCES `basic_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

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
  KEY `FKejwxxhh6d84nq0fw06chviiqu` (`role_id`),
  CONSTRAINT `FKejwxxhh6d84nq0fw06chviiqu` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKjqcbph1hwbmvesk9jyg6vqcw6` FOREIGN KEY (`module_id`) REFERENCES `sys_module` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
