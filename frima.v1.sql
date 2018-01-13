/*
Navicat MySQL Data Transfer

Source Server         : thomcvps
Source Server Version : 50505
Source Host           : 13.76.143.77:3306
Source Database       : frima

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-11-23 14:42:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for mst_user
-- ----------------------------
DROP TABLE IF EXISTS `mst_user`;
CREATE TABLE `mst_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT 'User name',
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `temp_password` varchar(255) DEFAULT NULL COMMENT 'temp password when reset pass',
  `salt` varchar(255) DEFAULT NULL COMMENT 'Salt to hash password',
  `status` enum('confirm','delete','active') DEFAULT NULL COMMENT 'Status: Active|Confirm|Deleted',
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `activation_key` varchar(255) DEFAULT NULL COMMENT 'Activation key when register',
  `access_token` varchar(255) DEFAULT NULL COMMENT 'token when login',
  `is_sys_user` int(1) DEFAULT NULL,
  `provider_type` enum('facebook','local','google') DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mst_user
-- ----------------------------
INSERT INTO `mst_user` VALUES ('1', 'thomc', 'thomc', '1b772cc88b7506ea8700badd0ff5edf07d44d5c4fe76f19cf8ac42b2107e2b1f', '123', '123', 'active', '2017-11-20 17:12:58', '1', '2017-11-20 17:13:05', '1', '123', '7d8ba843-5fb2-4853-9907-7b944314de94', '1', 'local', 'admin', '0');
INSERT INTO `mst_user` VALUES ('2', 'tantn', 'loc', '1b772cc88b7506ea8700badd0ff5edf07d44d5c4fe76f19cf8ac42b2107e2b1f', '123', '123', 'active', '2017-11-20 17:12:58', '1', '2017-11-20 17:13:05', '1', '123', '0f50e1a4-95da-4106-adab-44b2aba73bfb', '0', 'local', '', '0');
INSERT INTO `mst_user` VALUES ('3', 'minh', 'minh@gmail.com', '123', '123', '123', 'active', '2017-11-20 17:12:58', '2', '2017-11-20 17:12:58', '1', '123', null, '0', 'local', null, '0');


-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL COMMENT 'foreign key table mst_category',
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  `volumn_id` int(11) DEFAULT NULL COMMENT 'foreign key of mst_data_type\r with type volumn: \n50ml\r\n100ml\r\n200ml\r\n350ml\r\n375ml\r\n720ml\r\n750ml\r\n1,000ml\r\n1,500ml\r\n1,800ml\r\nその他\r\n',
  `volumn_value` decimal(10,0) DEFAULT NULL,
  `store_type_id` int(11) DEFAULT NULL COMMENT 'Foreign key table mst_data_type\r\n常温\r\n冷蔵庫\r\nワインセラー\r\n冷暗所\r\n倉庫\r\n',
  `product_status_id` int(11) DEFAULT NULL COMMENT 'Foreign key table mst_data_type\r\n状態良好\r\n状態不備\r\nラベル不良\r\n液面低下\r\n吹き跡有\r\n古酒\r\n新品\r\nその他\r\n',
  `tags` varchar(255) DEFAULT NULL COMMENT 'Separate by ;\r\nレアもの\r\n新品商品\r\n価格交渉OK\r\n',
  `ship_fee_type_id` int(11) DEFAULT NULL COMMENT 'Foreign key table mst_data_type\r\n送料込み（出品者負担）\r\n着払い（購入者負担）\r\n',
  `ship_method_id` int(11) DEFAULT NULL COMMENT 'Foreign key table mst_data_type\r\nクロネコヤマト\r\nゆうパック\r\nその他\r\n',
  `prefecture_id` int(11) DEFAULT NULL COMMENT 'Foreign key table mst_prefecture',
  `deliver_day_id` int(11) DEFAULT NULL COMMENT 'Foreign key table mst_data_type\r\n1～2日で発送\r\n2～3日で発送\r\n4～7日で発送\r\n',
  `price` decimal(10,0) DEFAULT NULL,
  `fee` decimal(10,0) DEFAULT NULL,
  `profit` decimal(10,0) DEFAULT NULL,
  `product_image_url` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL COMMENT 'draft|published|completed|buying|cancel',
  `product_type` int(11) DEFAULT NULL COMMENT '0: product; 1: collection',
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_product
-- ----------------------------
INSERT INTO `tbl_product` VALUES ('1', 'Product1', '1', '2017-11-21 17:15:58', '1', '2017-11-21 17:16:02', '1', 'Description', '1', '1', '1', '1', '123,123', '1', '1', '1', '1', '10000', null, '10000', 'https://www.google.com.vn/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png', 'published', '0', 'category name');
INSERT INTO `tbl_product` VALUES ('2', 'Product2', '1', '2017-11-21 17:38:04', '1', '2017-11-21 17:38:10', '1', 'Description 2', '1', '1', '1', '1', '123', '1', '1', '1', '1', '1000', null, '1000', 'https://www.google.com.vn/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png', 'published', '0', 'category name');

-- ----------------------------
-- Records of tbl_todo
-- ----------------------------
