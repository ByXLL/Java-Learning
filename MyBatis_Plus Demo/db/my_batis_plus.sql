/*
 Navicat Premium Data Transfer

 Source Server         : 本地主机
 Source Server Type    : MySQL
 Source Server Version : 50651
 Source Host           : localhost:3306
 Source Schema         : my_batis_plus

 Target Server Type    : MySQL
 Target Server Version : 50651
 File Encoding         : 65001

 Date: 16/04/2021 21:57:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `goods_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `goods_status` int(1) NOT NULL COMMENT '商品状态',
  `store_cate_id` int(11) NOT NULL COMMENT '商品分类id',
  `goods_count` int(11) NOT NULL COMMENT '商品个数',
  `is_del` int(1) NOT NULL COMMENT '是否删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_cate
-- ----------------------------
DROP TABLE IF EXISTS `goods_cate`;
CREATE TABLE `goods_cate`  (
  `cate_id` int(11) NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `is_del` int(1) NOT NULL,
  `cate_pid` int(11) NOT NULL,
  PRIMARY KEY (`cate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_cate
-- ----------------------------

-- ----------------------------
-- Table structure for goods_desc
-- ----------------------------
DROP TABLE IF EXISTS `goods_desc`;
CREATE TABLE `goods_desc`  (
  `desc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '详情id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详情信息',
  PRIMARY KEY (`desc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品详情' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_desc
-- ----------------------------

-- ----------------------------
-- Table structure for goods_main_pics
-- ----------------------------
DROP TABLE IF EXISTS `goods_main_pics`;
CREATE TABLE `goods_main_pics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主图id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品大图' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_main_pics
-- ----------------------------

-- ----------------------------
-- Table structure for goods_sub_pics
-- ----------------------------
DROP TABLE IF EXISTS `goods_sub_pics`;
CREATE TABLE `goods_sub_pics`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '小图id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品图片url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品小图' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_sub_pics
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `version` int(10) NULL DEFAULT 1 COMMENT '乐观锁',
  `is_del` int(1) NULL DEFAULT 0 COMMENT '是否删除 0 否，1 是',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 11, 'test1@baomidou.com', 2, 1, '2021-04-15 09:55:05', '2021-04-15 11:24:03');
INSERT INTO `user` VALUES (2, '李四', 20, 'test2@baomidou.com', 1, 0, '2021-04-15 09:55:05', '2021-04-15 09:55:05');
INSERT INTO `user` VALUES (3, '王五', 28, 'test3@163.com', 1, 0, '2021-04-15 09:55:05', '2021-04-15 09:55:05');
INSERT INTO `user` VALUES (4, '赵六', 21, 'test4@baomidou.com', 1, 0, '2021-04-15 09:55:05', '2021-04-15 09:55:05');
INSERT INTO `user` VALUES (5, '小明', 24, 'test5@baomidou.com', 1, 0, '2021-04-15 09:55:05', '2021-04-15 09:55:05');
INSERT INTO `user` VALUES (1382328618582482946, '狗哥', 10, '163@163.com', 1, 0, '2021-04-15 09:55:05', '2021-04-15 10:02:58');
INSERT INTO `user` VALUES (1382520714299871233, '狗哥', 20, '123@163.com', 1, 0, '2021-04-15 10:26:39', '2021-04-15 10:28:46');

SET FOREIGN_KEY_CHECKS = 1;
