/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : courselearning

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 10/07/2021 16:28:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `metadata` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `is_valid` tinyint(1) NOT NULL DEFAULT 1,
  `is_sharable` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `school` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  `delete_time` datetime(0) NULL DEFAULT NULL,
  `cost` int NOT NULL,
  `teacher_id` int NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_course`(`teacher_id`) USING BTREE,
  CONSTRAINT `fk_user_course` FOREIGN KEY (`teacher_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '软件工程与计算 I', '中级', '通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式', 'course1.png', '南京大学', '2020-12-20 10:00:00', NULL, 1, 1, '刘钦');
INSERT INTO `course` VALUES (2, '软件工程与计算 II', '中级', '基于DevOps培养团队开发中小规模软件系统的能力', 'course2.png', '南京大学', '2020-12-20 10:00:00', NULL, 1, 1, '刘钦');
INSERT INTO `course` VALUES (3, '道德与人生', '高级', '无', 'course3.png', '教育部', '2021-07-09 22:28:12', NULL, 100, 4, '桃子');
INSERT INTO `course` VALUES (4, '道德与人生2', '高级', '无', 'course4.png', '教育部', '2021-07-09 22:28:12', NULL, 100, 4, '桃子');

-- ----------------------------
-- Table structure for course_likes
-- ----------------------------
DROP TABLE IF EXISTS `course_likes`;
CREATE TABLE `course_likes`  (
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`course_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_likes
-- ----------------------------
INSERT INTO `course_likes` VALUES (1, 1);

-- ----------------------------
-- Table structure for course_order
-- ----------------------------
DROP TABLE IF EXISTS `course_order`;
CREATE TABLE `course_order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `origin` int NULL DEFAULT NULL,
  `cost` int NOT NULL,
  `course_id` int NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `user_id` int NOT NULL,
  `status` int NOT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `purchase_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_course_order`(`course_id`) USING BTREE,
  INDEX `fk_user_order`(`user_id`) USING BTREE,
  CONSTRAINT `fk_course_order` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_user_order` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_order
-- ----------------------------
INSERT INTO `course_order` VALUES (7, 100, 100, 3, '道德与人生', '2021-07-09 15:20:22', 5, 1, NULL, 'Buy');
INSERT INTO `course_order` VALUES (8, 1, 1, 1, '软件工程与计算 I', '2021-07-09 15:20:57', 5, 2, NULL, 'Buy');
INSERT INTO `course_order` VALUES (9, 1, 1, 2, '软件工程与计算 II', '2021-07-09 15:21:00', 5, 2, NULL, 'Buy');
INSERT INTO `course_order` VALUES (10, NULL, 25, 2, '软件工程与计算 II', '2021-07-09 16:07:40', 5, 1, '2021-10-08 15:52:58', 'Rent');
INSERT INTO `course_order` VALUES (11, NULL, 28, 2, '软件工程与计算 II', '2021-07-09 15:31:30', 5, 1, '2021-08-12 16:00:00', 'Rent');
INSERT INTO `course_order` VALUES (12, NULL, 28, 1, '软件工程与计算 I', '2021-07-09 15:49:20', 5, 2, '2021-08-13 15:49:20', 'Rent');

-- ----------------------------
-- Table structure for course_order_coupon
-- ----------------------------
DROP TABLE IF EXISTS `course_order_coupon`;
CREATE TABLE `course_order_coupon`  (
  `order_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  PRIMARY KEY (`order_id`, `coupon_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_order_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for course_ware
-- ----------------------------
DROP TABLE IF EXISTS `course_ware`;
CREATE TABLE `course_ware`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `number` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_size` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `free_flag` tinyint(1) NOT NULL DEFAULT 0,
  `download_flag` tinyint(1) NOT NULL DEFAULT 1,
  `upload_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_course_ware`(`course_id`) USING BTREE,
  CONSTRAINT `fk_course_ware` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 198 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_ware
-- ----------------------------
INSERT INTO `course_ware` VALUES (2, 1, 1, '00 - Overview', NULL, NULL, NULL, 0, 1, '2020-12-18 10:00:00');
INSERT INTO `course_ware` VALUES (3, 1, 2, '01 - 编程语言概述', NULL, NULL, NULL, 0, 1, '2020-12-18 10:00:00');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `post_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `post_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `post_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (8, 3, 5, '测试人员的自我修养', '1.学会熬夜', '2021-07-10 00:40:00');
INSERT INTO `post` VALUES (9, 3, 5, '测试人员的自我修养', '1.1学会熬夜', '2021-07-10 06:02:00');
INSERT INTO `post` VALUES (10, 3, 5, '测试人员的自我修养', '1.1.1学会熬夜', '2021-07-14 23:40:00');
INSERT INTO `post` VALUES (11, 3, 5, '测试人员的自我修养', '2.学会熬夜', '2021-07-10 00:40:00');
INSERT INTO `post` VALUES (13, 3, 5, '测试人员的自我修养', '3.学会熬夜', '2021-07-10 00:40:00');

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_id` int NOT NULL,
  `question_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (16, '世界上最飒的人是谁？', '助教小姐姐', '飒不需要理由', 3, 'ask');
INSERT INTO `question` VALUES (17, '世界上最帅的人是谁？', '助教小哥哥', '帅不需要理由', 3, 'ask');
INSERT INTO `question` VALUES (18, '世界上最帅的人是谁？', '助教小哥哥', '帅不需要理由', 3, 'ask');

-- ----------------------------
-- Table structure for recharge_order
-- ----------------------------
DROP TABLE IF EXISTS `recharge_order`;
CREATE TABLE `recharge_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `value` int NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk_user_recharge`(`user_id`) USING BTREE,
  CONSTRAINT `fk_user_recharge` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of recharge_order
-- ----------------------------

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `reply_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NULL DEFAULT NULL,
  `reply_user` int NOT NULL,
  `replied_user` int NOT NULL,
  `reply_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reply_time` datetime(0) NOT NULL,
  `replied_id` int NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES (10, 8, 4, 0, '2.学会使用工具', '2021-07-12 04:44:33', NULL, 1);
INSERT INTO `reply` VALUES (11, 8, 3, 0, '2.2学会使用工具', '2021-07-12 04:44:33', NULL, 1);
INSERT INTO `reply` VALUES (12, 8, 2, 0, '2.2.2学会使用工具', '2021-07-12 04:44:33', NULL, 0);
INSERT INTO `reply` VALUES (13, 8, 1, 0, '2.2.2.2学会使用工具', '2021-07-12 04:44:33', NULL, 0);
INSERT INTO `reply` VALUES (14, NULL, 1, 4, '3.学会debug', '2021-07-12 05:44:33', 10, 1);
INSERT INTO `reply` VALUES (15, NULL, 2, 4, '3.3.学会debug', '2021-07-12 05:44:33', 10, 1);
INSERT INTO `reply` VALUES (16, NULL, 3, 4, '3.3.3.学会debug', '2021-07-12 05:44:33', 10, 0);
INSERT INTO `reply` VALUES (17, NULL, 1, 3, '4.学会放弃', '2021-07-12 06:44:33', 16, 1);
INSERT INTO `reply` VALUES (18, NULL, 2, 3, '4.4.学会放弃', '2021-07-12 06:44:33', 16, 1);
INSERT INTO `reply` VALUES (19, NULL, 1, 2, '5.遇到困难睡大觉', '2021-07-12 07:44:33', 18, 0);
INSERT INTO `reply` VALUES (21, NULL, 1, 2, '5.遇到困难睡大觉', '2021-07-12 07:44:33', 18, 0);
INSERT INTO `reply` VALUES (22, 13, 4, 5, '2.学会使用工具', '2021-07-12 04:44:33', NULL, 0);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `test_id` int NOT NULL AUTO_INCREMENT,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  `test_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `test_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`test_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (24, '2021-07-16 16:00:00', '2021-07-24 16:00:00', 'IQ测试', 'toStart', '3');
INSERT INTO `test` VALUES (25, '2021-07-18 16:00:00', '2021-07-26 16:00:00', 'IQ测试2', 'toStart', '3');
INSERT INTO `test` VALUES (26, '2021-07-21 16:00:00', '2021-07-29 16:00:00', 'ZZ测试', 'toStart', '4');

-- ----------------------------
-- Table structure for test_question
-- ----------------------------
DROP TABLE IF EXISTS `test_question`;
CREATE TABLE `test_question`  (
  `test_id` int NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`test_id`, `question_id`) USING BTREE,
  INDEX `question_id`(`question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_question
-- ----------------------------
INSERT INTO `test_question` VALUES (24, 16);
INSERT INTO `test_question` VALUES (24, 17);
INSERT INTO `test_question` VALUES (24, 18);

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon`  (
  `user_id` int NOT NULL,
  `coupon_id` int NOT NULL,
  `nums` int NOT NULL,
  PRIMARY KEY (`user_id`, `coupon_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `balance` int NOT NULL,
  `user_role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  `is_vip` int NOT NULL DEFAULT 0,
  `vip_end_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '刘钦', '10112345678', '123456', NULL, 0, 'TEACHER', '2020-12-18 10:00:00', 0, NULL);
INSERT INTO `user_info` VALUES (2, '丁笑宇', '11111111111', '123456', NULL, 0, 'STUDENT', '2020-12-18 10:00:00', 0, '2021-07-06 19:06:38');
INSERT INTO `user_info` VALUES (3, '陈耔为', '22222222222', '123456', NULL, 0, 'TEACHER', '2021-07-08 23:31:47', 0, NULL);
INSERT INTO `user_info` VALUES (4, '橙子', '33333333333', '123456', NULL, 99900, 'TEACHER', '2021-07-08 23:32:05', 1, '2021-09-17 00:00:00');
INSERT INTO `user_info` VALUES (5, '苹果', '44444444444', '123456', NULL, 99144, 'STUDENT', '2021-07-07 17:07:02', 1, '2021-07-16 17:16:47');

-- ----------------------------
-- Table structure for user_question
-- ----------------------------
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question`  (
  `user_id` int NOT NULL,
  `question_id` int NOT NULL,
  `test_id` int NOT NULL,
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_ox` int NOT NULL,
  PRIMARY KEY (`user_id`, `test_id`, `question_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_question
-- ----------------------------
INSERT INTO `user_question` VALUES (5, 16, 24, '小姐姐', 0);
INSERT INTO `user_question` VALUES (5, 17, 24, '小姐姐', 0);
INSERT INTO `user_question` VALUES (5, 18, 24, '小哥哥', 0);

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`  (
  `user_id` int NOT NULL,
  `test_id` int NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`user_id`, `test_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_test
-- ----------------------------
INSERT INTO `user_test` VALUES (5, 24, 0);

SET FOREIGN_KEY_CHECKS = 1;
