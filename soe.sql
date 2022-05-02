/*
Navicat MySQL Data Transfer

Source Server         : hgy
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : soe

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2022-05-08 17:41:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for active_uid
-- ----------------------------
DROP TABLE IF EXISTS `active_uid`;
CREATE TABLE `active_uid` (
  `nid` int NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL,
  `aid` int DEFAULT NULL,
  `finish` int DEFAULT '0',
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of active_uid
-- ----------------------------

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` int DEFAULT NULL,
  `location` int DEFAULT NULL,
  `Info` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `org_id` int DEFAULT NULL,
  `people_num` int DEFAULT NULL,
  `need_num` int DEFAULT '20',
  `mid` bigint DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '核酸', '2022-05-01', '15', '1', null, '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('2', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('3', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-04 22:13:11');
INSERT INTO `activity` VALUES ('4', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('5', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('6', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('7', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('8', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');
INSERT INTO `activity` VALUES ('9', '核酸', '2022-05-01', '15', '1', '', '1', '10', '20', null, '2022-05-19 22:13:11');

-- ----------------------------
-- Table structure for activity_img
-- ----------------------------
DROP TABLE IF EXISTS `activity_img`;
CREATE TABLE `activity_img` (
  `id` bigint NOT NULL,
  `src` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of activity_img
-- ----------------------------

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES ('1', 'https://soe.icemomo.com/u%3D2192832229%2C1696754652%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp', '大家一起努力争做志愿先锋队');
INSERT INTO `banner` VALUES ('2', 'https://soe.icemomo.com/u%3D2367654603%2C2706466966%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp', '志愿从我做起！');
INSERT INTO `banner` VALUES ('3', 'https://soe.icemomo.com/u%3D3345994588%2C1575757096%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG.webp', '共建美好家园');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `cid` int DEFAULT NULL,
  `likes` int DEFAULT '0',
  `context` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1521801067894796289', '4', '0', '爱你一万年鸭');
INSERT INTO `comment` VALUES ('2', '1521801067894796289', '7', '0', '爱你一万年鸭');

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community` (
  `community_id` int NOT NULL AUTO_INCREMENT,
  `publish_time` datetime DEFAULT NULL,
  `likes` int DEFAULT '0',
  `comment_sum` int DEFAULT NULL,
  `img_ids` bigint DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `context` varchar(500) DEFAULT NULL,
  `uid` bigint DEFAULT NULL,
  PRIMARY KEY (`community_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES ('6', '2022-05-07 20:18:15', '0', null, '6928679378880581633', '1', '这是帖子内容', '1521801067894796289');

-- ----------------------------
-- Table structure for community_img
-- ----------------------------
DROP TABLE IF EXISTS `community_img`;
CREATE TABLE `community_img` (
  `com_img_id` int NOT NULL AUTO_INCREMENT,
  `ids` bigint DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`com_img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of community_img
-- ----------------------------
INSERT INTO `community_img` VALUES ('1', '6928673162716532737', 'https://soe.icemomo.com/2eec0d67f1764f93a2d3c14ba0e222bd.jpg');
INSERT INTO `community_img` VALUES ('2', '6928679378880581633', 'https://soe.icemomo.com/76bc5736c0b94f1b89a227597cbbdaed.jpg');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `sid` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '苹果手机', '苹果手机', '999.00', '1', null);
INSERT INTO `goods` VALUES ('2', null, 'textsada', null, null, 'https://soe.icemomo.com/8490db59e6c54041841a39612d7c3217.png');

-- ----------------------------
-- Table structure for like_com
-- ----------------------------
DROP TABLE IF EXISTS `like_com`;
CREATE TABLE `like_com` (
  `like_id` int NOT NULL AUTO_INCREMENT,
  `uid` bigint DEFAULT NULL,
  `com_id` int DEFAULT NULL,
  PRIMARY KEY (`like_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of like_com
-- ----------------------------
INSERT INTO `like_com` VALUES ('1', '1521801067894796289', '4');

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `oid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sum_people` int DEFAULT NULL,
  `sum_time` int DEFAULT NULL,
  `service_object` varchar(10) DEFAULT '老人',
  `region` varchar(30) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of org
-- ----------------------------
INSERT INTO `org` VALUES ('1', '广东省广州市所依志愿者服总队', '11', '9999', '老人', '广东技术师范大学', '2022-05-04');
INSERT INTO `org` VALUES ('2', '广东省广州市所依志愿者服一队', '11', '9999', '老人', '广东技术师范大学', '2022-05-04');
INSERT INTO `org` VALUES ('3', '广东省广州市所依志愿者服二队', '11', '9999', '老人', '广东技术师范大学', '2022-05-04');
INSERT INTO `org` VALUES ('4', 'text', null, null, '老人', null, '2022-05-08');

-- ----------------------------
-- Table structure for posts_img
-- ----------------------------
DROP TABLE IF EXISTS `posts_img`;
CREATE TABLE `posts_img` (
  `id` int DEFAULT NULL,
  `img_url` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of posts_img
-- ----------------------------

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `catalogue_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES ('1', '数码', '1');
INSERT INTO `sort` VALUES ('2', '美妆', '2');
INSERT INTO `sort` VALUES ('3', '母婴', null);
INSERT INTO `sort` VALUES ('4', '办公', null);
INSERT INTO `sort` VALUES ('5', '医药', null);
INSERT INTO `sort` VALUES ('6', '家装', null);
INSERT INTO `sort` VALUES ('7', '玩具乐器', null);
INSERT INTO `sort` VALUES ('8', '海鲜生疏', null);
INSERT INTO `sort` VALUES ('9', '汽车用品', null);
INSERT INTO `sort` VALUES ('10', '男装', null);
INSERT INTO `sort` VALUES ('11', '女装', null);
INSERT INTO `sort` VALUES ('12', '食品', null);
INSERT INTO `sort` VALUES ('13', '计生情趣', null);

-- ----------------------------
-- Table structure for spot
-- ----------------------------
DROP TABLE IF EXISTS `spot`;
CREATE TABLE `spot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `location` varchar(30) DEFAULT NULL,
  `current` int DEFAULT NULL,
  `service_object` varchar(10) DEFAULT NULL,
  `context` varchar(60) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `pic_src` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of spot
-- ----------------------------
INSERT INTO `spot` VALUES ('1', '广东技术师范大学', '广东省广州市白云区江高镇', '43443', '学生', '维护核酸秩序', '高校', '2022-05-01', '');
INSERT INTO `spot` VALUES ('2', '广东技术师范大学', '广东省广州市白云区江高镇', '43443', '学生', '维护核酸秩序', '高校', '2022-05-01', '');
INSERT INTO `spot` VALUES ('3', '广东技术师范大学', '广东省广州市白云区江高镇', '43443', '学生', '维护核酸秩序', '高校', '2022-05-01', null);
INSERT INTO `spot` VALUES ('4', '广东技术师范大学', '广东省广州市白云区江高镇', '43443', '学生', '维护核酸秩序', '高校', '2022-05-01', null);
INSERT INTO `spot` VALUES ('5', '广东技术师范大学', '广东省广州市白云区江高镇', '43443', '学生', '维护核酸秩序', '高校', '2022-05-01', null);
INSERT INTO `spot` VALUES ('6', '广东技术师范大学', '广东省广州市白云区江高镇', '43443', '学生', '维护核酸秩序', '高校', '2022-05-01', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `password` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deleted` int DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1522844473903329282', 'i.gy@outlook.com', '202cb962ac59075b964b07152d234b70', '0', '张三');
INSERT INTO `user` VALUES ('1522844473903329283', '79006134@qq.com', 'f379eaf3c831b04de153469d1bec345e', '0', '张三');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `info_id` bigint NOT NULL,
  `phone` int DEFAULT NULL,
  `nickname` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '最可爱的人',
  `service_time` int DEFAULT '0',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `profession` varchar(10) DEFAULT NULL,
  `identity` varchar(20) DEFAULT NULL,
  `oid` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`info_id`),
  KEY `fk_info_org` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1522844473903329282', null, '林俊杰', '0', 'i.gy@outlook.com', 'https://soe.icemomo.com/0a74cf0e78e64df7a182bf3c6145db04.png', null, null, null, '张三', '2022-05-07 16:21:36');
INSERT INTO `user_info` VALUES ('1522844473903329283', null, '周杰伦', '0', '79006134@qq.com', null, null, null, '2', '张三', '2022-05-07 18:24:35');
