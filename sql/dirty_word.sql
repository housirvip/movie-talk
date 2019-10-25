/*
 Navicat Premium Data Transfer

 Source Server         : 35.193.61.114
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 35.193.61.114:3306
 Source Schema         : movie-talk

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 25/10/2019 17:44:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dirty_word
-- ----------------------------
DROP TABLE IF EXISTS `dirty_word`;
CREATE TABLE `dirty_word`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `language` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `word`(`word`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2298 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
