/*
 Navicat Premium Data Transfer

 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : Reamot

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 19/10/2020 15:58:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of doctor
-- ----------------------------
BEGIN;
INSERT INTO `doctor` VALUES (1, 'mintonmu', '123456');
COMMIT;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int NOT NULL,
  `pid` int DEFAULT NULL,
  `htime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `prescription` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of medicine
-- ----------------------------
BEGIN;
INSERT INTO `medicine` VALUES (7, 'Pantoprazole', 'The indications are suitable for active peptic ulcer (gastric, duodenal ulcer), reflux esophagitis and Zollinger-Ehrlich syndrome.', NULL);
INSERT INTO `medicine` VALUES (8, 'Amoxicillin', 'Amoxicillin, also known as amoxicillin or amoxicillin, is one of the most commonly used semi-synthetic penicillin broad-spectrum β-lactam antibiotics', NULL);
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_repeat` tinyint(1) DEFAULT NULL,
  `repeat_cnt` int DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `dose` int DEFAULT NULL,
  `has_dose` int DEFAULT NULL,
  `has_repeat_cnt` int DEFAULT NULL,
  `medic_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `medic_id` (`medic_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`medic_id`) REFERENCES `medicine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `medi_num` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patient
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for pharmacist
-- ----------------------------
DROP TABLE IF EXISTS `pharmacist`;
CREATE TABLE `pharmacist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pharmacist
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pid` int DEFAULT NULL,
  `medic_id` int DEFAULT NULL,
  `did` int DEFAULT NULL,
  `yid` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  KEY `medic_id` (`medic_id`),
  KEY `did` (`did`),
  KEY `yid` (`yid`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`medic_id`) REFERENCES `medicine` (`id`),
  CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`did`) REFERENCES `doctor` (`id`),
  CONSTRAINT `prescription_ibfk_3` FOREIGN KEY (`yid`) REFERENCES `pharmacist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of prescription
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `mnumber` int DEFAULT NULL,
  `medicine` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'mintonmu', 'mintonmu', 'mintonmu@outlook.com', '1231231231', 1231, 'Pantoprazole', 1);
INSERT INTO `user` VALUES (6, 'xd', 'xd', 'mintonmu@outlook.com', '1231231231', 1231, 'Pantoprazole', 1);
INSERT INTO `user` VALUES (7, 'cd', 'xd', 'mintonmu@outlook.com', '1231231231', 1231, 'Amoxicillin', 0);
INSERT INTO `user` VALUES (12, 'xxxx', 'adxxxmin', NULL, '12321312324', 11111, 'Amoxicillin', 0);
INSERT INTO `user` VALUES (16, 'xxxxxx', 'adxxxmin', '2859595067@qq.com', '12321312324', 11111, 'Pantoprazole', 1);
INSERT INTO `user` VALUES (19, 'xxxxxxxx', 'adxxxmin', NULL, '12321312324', 11111, 'Amoxicillin', 1);
INSERT INTO `user` VALUES (23, 'adwda', 'adwad', '2859595067@qq.com', '123214124', 12313, 'Pantoprazole', 1);
INSERT INTO `user` VALUES (24, 'xxxxxxxxxxx', '21241424321', '2859595067@qq.com', '321424124', 1232, 'Amoxicillin', 1);
INSERT INTO `user` VALUES (25, 'AAAA', '123213214', 'AAAAAAA', '1232132124', 1231, 'Pantoprazole', 0);
INSERT INTO `user` VALUES (26, 'BBBBBB', 'wdawdaw', 'Wdadawdad', '1231323', 123, 'Pantoprazole', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
