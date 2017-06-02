/*
Navicat MySQL Data Transfer

Source Server         : localhost_5.7
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : backend

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-02 17:39:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `service`
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `ID` varchar(32) NOT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(11) DEFAULT NULL,
  `SERVICE_GROUP_ID` varchar(255) DEFAULT NULL,
  `SHORT_DES` varchar(255) DEFAULT NULL,
  `FULL_DES` varchar(11) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(255) DEFAULT NULL,
  `ICON_URL` varchar(255) DEFAULT NULL,
  `IMG_DES_URL` varchar(255) DEFAULT NULL,
  `WEB_LINK` varchar(255) DEFAULT NULL,
  `SHORT_CODE` varchar(255) DEFAULT NULL,
  `ACTION_TYPE` int(11) DEFAULT NULL,
  `SERVICE_TYPE` int(11) DEFAULT NULL,
  `APPROVED` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `LANGUAGE` varchar(255) DEFAULT NULL,
  `PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('1', '123', '123', '123', '123', '123', '2017-06-06 17:36:57', '123', '2017-06-17 17:37:02', '123', '123', '123', '132', '132', '123', '123', '123', '1', '123', '123');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(32) NOT NULL,
  `FULL_NAME` varchar(255) DEFAULT NULL,
  `ROLE` int(11) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATED_TIME` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `LAST_UPDATED_TIME` datetime DEFAULT NULL,
  `LAST_UPDATED_BY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
