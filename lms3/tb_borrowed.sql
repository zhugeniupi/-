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

 Date: 13/07/2023 09:46:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_borrowed
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrowed`;
CREATE TABLE `tb_borrowed`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `person_id` int NULL DEFAULT NULL COMMENT '借书人ID',
  `borrowed_id` int NULL DEFAULT NULL COMMENT '借书编号',
  `borrowed_day` date NULL DEFAULT NULL COMMENT '借阅日期',
  `return_day` date NULL DEFAULT NULL COMMENT '归还日期',
  `over_day` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否超出归还日期，0表示没有，1表示有',
  `give_back` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否归还，0表示没有，1表示有',
  `contack` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '借阅表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_borrowed
-- ----------------------------
INSERT INTO `tb_borrowed` VALUES (1, 4, 1, '2023-02-14', '2023-05-14', '0', '1', '1726666663');
INSERT INTO `tb_borrowed` VALUES (2, 8, 2, '2023-03-08', '2023-06-08', '0', '1', '19957888828');

SET FOREIGN_KEY_CHECKS = 1;
