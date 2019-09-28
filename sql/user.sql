/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MariaDB
 Source Server Version : 100407
 Source Host           : localhost:3306
 Source Schema         : movie-talk

 Target Server Type    : MariaDB
 Target Server Version : 100407
 File Encoding         : 65001

 Date: 27/09/2019 21:51:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `role` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp(),
  `enable` tinyint(1) DEFAULT 1,
  `level` int(4) NOT NULL DEFAULT 0,
  `group` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `username` (`username`),
  KEY `email` (`email`),
  KEY `phone` (`phone`),
  KEY `login` (`username`,`email`,`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
