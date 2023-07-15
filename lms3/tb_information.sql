/*
 Navicat Premium Data Transfer

 Source Server         : shixun
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 13/07/2023 09:46:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_information
-- ----------------------------
DROP TABLE IF EXISTS `tb_information`;
CREATE TABLE `tb_information`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '图书号',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书类型',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书名称',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作者名称',
  `publish` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `count` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '总数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_information
-- ----------------------------
INSERT INTO `tb_information` VALUES (1, '国外经典著作', '《瓦尔登湖》', '[美]亨利·戴维·梭罗', '浙江文艺出版社', '20');
INSERT INTO `tb_information` VALUES (2, '国外经典著作', '《秘密》', '[澳]朗达·拜恩', '中国城市出版社', '30');
INSERT INTO `tb_information` VALUES (3, '言情都市小说', '《面包树上的女人》', '张小娴', '南海出版公司', '10');
INSERT INTO `tb_information` VALUES (4, '现代国内文学', '《写给幸福》', '席慕蓉', '云南人民出版社', '25');
INSERT INTO `tb_information` VALUES (5, '现代国内文学', '《水知道答案》', '江本胜', '南海出版公司', '15');
INSERT INTO `tb_information` VALUES (6, '人物传记类', '《田间筑梦:共和国功勋袁隆平》', '陈启文', '浙江人民出版社', '91');
INSERT INTO `tb_information` VALUES (7, '人物传记类', '《名人传》', '[法]罗曼·罗兰', '湖南文艺出版社', '45');
INSERT INTO `tb_information` VALUES (8, '国外经典著作', '《假如给我三天光明》', '[美]海伦·凯勒', '北京燕山出版社', '65');
INSERT INTO `tb_information` VALUES (9, '现代文学著作', '《文城》', '余华', '新经典文化出版社', '70');
INSERT INTO `tb_information` VALUES (10, '现代国内文学', '《认知觉醒：开启自我改变的原动力》', '周岭', '人民邮电出版社', '5');

SET FOREIGN_KEY_CHECKS = 1;
