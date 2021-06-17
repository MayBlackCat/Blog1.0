/*
 Navicat Premium Data Transfer

 Source Server         : mysql80
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : helloblog

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 05/06/2021 15:22:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `blogPicture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `flags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `appreciate` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commentable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `userId` int(20) NULL DEFAULT NULL,
  `typeId` int(20) NULL DEFAULT NULL,
  `tagsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `views` int(255) NULL DEFAULT NULL,
  `recommend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `publish` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `published` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blogtag
-- ----------------------------
DROP TABLE IF EXISTS `blogtag`;
CREATE TABLE `blogtag`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogtag
-- ----------------------------
INSERT INTO `blogtag` VALUES (1, '64646456');
INSERT INTO `blogtag` VALUES (2, '52425');
INSERT INTO `blogtag` VALUES (3, 'day');
INSERT INTO `blogtag` VALUES (4, '454');
INSERT INTO `blogtag` VALUES (5, 'haolong');
INSERT INTO `blogtag` VALUES (6, '453453');
INSERT INTO `blogtag` VALUES (7, '5445');
INSERT INTO `blogtag` VALUES (8, 'wwww');

-- ----------------------------
-- Table structure for blogtype
-- ----------------------------
DROP TABLE IF EXISTS `blogtype`;
CREATE TABLE `blogtype`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogtype
-- ----------------------------
INSERT INTO `blogtype` VALUES (8, 'g');
INSERT INTO `blogtype` VALUES (9, 'vvv');
INSERT INTO `blogtype` VALUES (11, '4534534534');
INSERT INTO `blogtype` VALUES (13, 'fasff');
INSERT INTO `blogtype` VALUES (14, 'dda');
INSERT INTO `blogtype` VALUES (15, 'kuku');
INSERT INTO `blogtype` VALUES (16, '444');
INSERT INTO `blogtype` VALUES (17, 'zhouzhesheng');
INSERT INTO `blogtype` VALUES (18, 'chaoshengjie');
INSERT INTO `blogtype` VALUES (21, '??');

-- ----------------------------
-- Table structure for bloguser
-- ----------------------------
DROP TABLE IF EXISTS `bloguser`;
CREATE TABLE `bloguser`  (
  `id` int(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bloguser
-- ----------------------------
INSERT INTO `bloguser` VALUES (1, 'cat', 'e10adc3949ba59abbe56e057f20f883e', '1923384291@qq.com', 'http://img.jj20.com/up/allimg/tp09/210521121249CW-0-lp.jpg', '2021-06-02 16:55:00', '2021-06-02 16:55:05');
INSERT INTO `bloguser` VALUES (2, 'aj', 'c4ca4238a0b923820dcc509a6f75849b', '1923384191@qq.com', 'http://img.jj20.com/up/allimg/tp08/24041324321198-lp.jpg', '2021-06-04 14:33:18', '2021-06-04 14:33:22');

-- ----------------------------
-- Table structure for t_blog_comment_prt
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_comment_prt`;
CREATE TABLE `t_blog_comment_prt`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `blog_id` int(20) NULL DEFAULT NULL,
  `parent_id` int(20) NULL DEFAULT NULL,
  `son_id` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog_comment_son
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_comment_son`;
CREATE TABLE `t_blog_comment_son`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `blog_id` int(20) NULL DEFAULT NULL,
  `parent_id` int(20) NULL DEFAULT NULL,
  `son_id` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
