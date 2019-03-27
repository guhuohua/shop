/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : wxapp

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-17 22:48:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_address
-- ----------------------------
DROP TABLE IF EXISTS `tbl_address`;
CREATE TABLE `tbl_address` (
  `addr_id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `nikename` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `selected` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`addr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_address
-- ----------------------------
INSERT INTO `tbl_address` VALUES ('1', '1', '湖北 - 黄石- 黄石区', '球球', null, '1', '1', '1', '1');

-- ----------------------------
-- Table structure for tbl_dividend
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dividend`;
CREATE TABLE `tbl_dividend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `ord_id` int(11) DEFAULT NULL,
  `source_user_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_dividend
-- ----------------------------
INSERT INTO `tbl_dividend` VALUES ('1', '1539703698545', '112.8', '1', '1', '0');
INSERT INTO `tbl_dividend` VALUES ('2', '1539703698574', '112.8', '1', '1', '0');

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES ('1', '1539315022596', '球球', '123456', '1', '1234567890', '123456', 'http://m.vstou.com/img/201601/alq3.jpg');

-- ----------------------------
-- Table structure for tbl_employee_roles
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee_roles`;
CREATE TABLE `tbl_employee_roles` (
  `employee_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  UNIQUE KEY `UK_q9ljqmnb2oq77nk5pqvc5cgok` (`roles_id`),
  KEY `FKjvq14ruqgs8y7c0c2qkfe61yx` (`employee_id`),
  CONSTRAINT `FKewmhsxi7bjk2npanibyxf6r9s` FOREIGN KEY (`roles_id`) REFERENCES `tbl_role` (`id`),
  CONSTRAINT `FKjvq14ruqgs8y7c0c2qkfe61yx` FOREIGN KEY (`employee_id`) REFERENCES `tbl_employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_employee_roles
-- ----------------------------
INSERT INTO `tbl_employee_roles` VALUES ('1', '1');

-- ----------------------------
-- Table structure for tbl_goods
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods`;
CREATE TABLE `tbl_goods` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `g_flag` varchar(255) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `ori_price` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `repertory` int(11) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`g_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_goods
-- ----------------------------
INSERT INTO `tbl_goods` VALUES ('1', '1534747996962', '我是商品描述', 'DZPV4MSPycbRfSwS', '1', 'http://117.50.74.117:8080/image/goods/pic04.png', '导航', null, '1430.12', '1100.1', '417', '89', '2', '1', '大众迈腾POLO速腾途观捷达新朗逸宝来桑塔纳倒车影像导航一体机', '15', '个');
INSERT INTO `tbl_goods` VALUES ('2', '1534747996994', '我是商品描述', 'z8Wb3haAhnSvnzIq', '1', 'http://117.50.74.117:8080/image/goods/pic01.png', '导航', null, '3484', '2680', '535', '30', '2', '1', '宝马新X1X3X4X5X6 1系2系3系5系7系倒车影像安卓大屏导航仪一体机', '23', '个');
INSERT INTO `tbl_goods` VALUES ('3', '1534747996998', '我是商品描述', 'jpbb9oclUGxflXLy', '1', 'http://117.50.74.117:8080/image/goods/pic02.png', '导航', null, '2834', '2180', '54', '6', '2', '1', '18款凯迪拉克ATSL SRX XTS 赛威10.4安卓4G大屏导航360倒车影像 ', '22', '个');
INSERT INTO `tbl_goods` VALUES ('4', '1534747997002', '我是商品描述', 'buYKApQnc8UziLYi', '1', 'http://117.50.74.117:8080/image/goods/pic03.png', '导航', null, '4134', '3180', '213', '48', '1', '1', '奥迪新老Q5/Q3/A4L/A3/A5改装8.8/10.25寸车机导航大屏一体机原厂  ', '5', '个');
INSERT INTO `tbl_goods` VALUES ('5', '1534747997006', '我是商品描述', 'nskmLoCFtlf5eWA2', '1', 'http://117.50.74.117:8080/image/goods/pic05.png', '导航', null, '89.44', '68.8', '331', '48', '2', '1', '汽车雨刷新宝来速腾无骨通用原装升级福克斯科鲁兹凯越朗逸雨刮器 ', '21', '个');
INSERT INTO `tbl_goods` VALUES ('6', '1534747997013', '我是商品描述', '4jbpTTJQPZD73FGx', '1', 'http://117.50.74.117:8080/image/goods/pic06.jpg', '导航', null, '219.44', '168.8', '604', '17', '1', '1', '博世无骨雨刷片奥迪A4L迈腾A6L途观Q5新帕萨特明锐速腾汽车雨刮器', '14', '个');
INSERT INTO `tbl_goods` VALUES ('7', '1534747997017', '我是商品描述', 'Xyx1AnbLbDUOlWP0', '1', 'http://117.50.74.117:8080/image/goods/pic07.jpg', '导航', null, '89.44', '68.8', '641', '79', '2', '1', '大众原装朗逸速腾宝来polo迈腾雨刮器高尔夫帕萨特途观cc原厂雨刷 ', '14', '个');
INSERT INTO `tbl_goods` VALUES ('8', '1534747997020', '我是商品描述', 'QbDqc0Ubz35PdTSf', '1', 'http://117.50.74.117:8080/image/goods/pic08.jpg', '导航', null, '24.44', '18.8', '789', '59', '1', '1', '车内禁止吸烟车贴NOSMOKING禁烟贴纸车内请勿吸烟提示标志贴', '19', '个');
INSERT INTO `tbl_goods` VALUES ('9', '1534747997024', '我是商品描述', 'mxXXtfWfMFhXxzj0', '1', 'http://117.50.74.117:8080/image/goods/pic09.jpg', '导航', null, '37.44', '28.8', '239', '93', '1', '1', '专用于朗动 内饰闪光膜碳纤贴膜 改装贴车内贴纸 中控碳纤维贴纸', '18', '个');
INSERT INTO `tbl_goods` VALUES ('10', '1534747997028', '我是商品描述', '2cINFP6VlaqI3NiX', '1', 'http://117.50.74.117:8080/image/goods/pic10.jpg', '导航', null, '102.44', '78.8', '134', '11', '1', '1', '十代思域改装专用装饰内饰贴纸16款本田新思域中控改色碳纤维贴膜 ', '6', '个');
INSERT INTO `tbl_goods` VALUES ('11', '1534747997031', '我是商品描述', 'kGSYm9tzotwgfoke', '1', 'http://117.50.74.117:8080/image/goods/pic11.jpg', '导航', null, '28.34', '21.8', '165', '24', '1', '1', '汽车内饰贴纸车门改色装饰膜车身贴划痕个性遮挡改装钓箱贴画防水', '17', '个');
INSERT INTO `tbl_goods` VALUES ('12', '1534747997035', '我是商品描述', 'VwuHrQJLmNlwIYj4', '1', 'http://117.50.74.117:8080/image/goods/pic12.jpg', '导航', null, '15.34', '11.8', '567', '70', '1', '1', '汽车碳纤维贴膜碳纤维贴纸整车贴碳纤维布内饰装饰全车贴汽车贴膜', '9', '个');
INSERT INTO `tbl_goods` VALUES ('13', '1534747997039', '我是商品描述', 'iS1VTZG3Aui4CJRV', '1', 'http://117.50.74.117:8080/image/goods/pic13.jpg', '导航', null, '81.64', '62.8', '624', '62', '1', '1', '北京现代朗动领动改装车贴花专用雨刷器贴纸装饰雨刮个性创意内饰', '10', '个');
INSERT INTO `tbl_goods` VALUES ('14', '1534747997042', '我是商品描述', '3cuOTCegOA7T4lv1', '1', 'http://117.50.74.117:8080/image/goods/pic14.jpg', '导航', null, '15.73', '12.1', '515', '5', '2', '1', '汽车用品蜡拖除尘掸子擦车拖把洗车帮手软毛刷车刷子清洁工具专用', '11', '个');
INSERT INTO `tbl_goods` VALUES ('15', '1534747997045', '我是商品描述', 'DqsQR9Kqt10YjnEE', '1', 'http://117.50.74.117:8080/image/ad/pic01.png', '导航', null, '24.83', '19.1', '397', '5', '1', '1', '洗车拖把专用刷车用刷子长柄伸缩式非纯棉多功能软毛汽车擦车工具', '7', '个');
INSERT INTO `tbl_goods` VALUES ('17', '1534747997051', '我是商品描述', '9vknw70YqxsOJp6h', '1', 'http://117.50.74.117:8080/image/ad/pic03.png', '导航', null, '77.87', '59.9', '113', '0', '1', '1', '车载HUD高清抬头显示器汽车通用行车电脑OBD平视速度多功能投影仪', '8', '个');
INSERT INTO `tbl_goods` VALUES ('18', '1534747997054', '我是商品描述', 'aeMr60KLzJmsAnI3', '1', 'http://117.50.74.117:8080/image/ad/pic04.png', '导航', null, '129.87', '99.9', '291', '4', '1', '1', '汽车车载HUD抬头显示器 OBD车速水温投影仪 途驰安A100高清通用', '14', '个');
INSERT INTO `tbl_goods` VALUES ('19', '1535082708218', '我是商品描述', 'qobHsAwFtbNQ3Gx2', '1', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg', '导航', null, '1790.9', '999.9', '9999', '90', '1', '1', '新途观L迈腾奥德赛智能车机导航仪车载中控流媒体倒车影像一体机', '13', '个');
INSERT INTO `tbl_goods` VALUES ('30', '1539746158819', '简介', '11111111', '0', 'http://localhost:8080/storage/fetch/pvHLEuCgxBaVJEAEMwOa.jpg', '垫子', null, '123', '123', '100', null, '1', '0', '12', '11', '个');

-- ----------------------------
-- Table structure for tbl_goods_ad
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_ad`;
CREATE TABLE `tbl_goods_ad` (
  `ga_id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_type` int(11) DEFAULT NULL,
  `g_flag` varchar(255) DEFAULT NULL,
  `g_id` int(11) DEFAULT NULL,
  `g_price` double DEFAULT NULL,
  `g_title` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`ga_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_goods_ad
-- ----------------------------
INSERT INTO `tbl_goods_ad` VALUES ('1', '3', 'DZPV4MSPycbRfSwS', '1', '1100.1', '大众迈腾POLO速腾途观捷达新朗逸宝来桑塔纳倒车影像导航一体机', 'http://117.50.74.117:8080/image/goods/pic04.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('2', '2', 'z8Wb3haAhnSvnzIq', '2', '2680', '宝马新X1X3X4X5X6 1系2系3系5系7系倒车影像安卓大屏导航仪一体机', 'http://117.50.74.117:8080/image/goods/pic01.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('3', '2', 'jpbb9oclUGxflXLy', '3', '2180', '18款凯迪拉克ATSL SRX XTS 赛威10.4安卓4G大屏导航360倒车影像 ', 'http://117.50.74.117:8080/image/goods/pic02.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('4', '3', 'buYKApQnc8UziLYi', '4', '3180', '奥迪新老Q5/Q3/A4L/A3/A5改装8.8/10.25寸车机导航大屏一体机原厂  ', 'http://117.50.74.117:8080/image/goods/pic03.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('5', '3', 'nskmLoCFtlf5eWA2', '5', '68.8', '汽车雨刷新宝来速腾无骨通用原装升级福克斯科鲁兹凯越朗逸雨刮器 ', 'http://117.50.74.117:8080/image/goods/pic05.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('6', '3', '4jbpTTJQPZD73FGx', '6', '168.8', '博世无骨雨刷片奥迪A4L迈腾A6L途观Q5新帕萨特明锐速腾汽车雨刮器', 'http://117.50.74.117:8080/image/goods/pic06.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('7', '2', 'Xyx1AnbLbDUOlWP0', '7', '68.8', '大众原装朗逸速腾宝来polo迈腾雨刮器高尔夫帕萨特途观cc原厂雨刷 ', 'http://117.50.74.117:8080/image/goods/pic07.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('8', '3', 'QbDqc0Ubz35PdTSf', '8', '18.8', '车内禁止吸烟车贴NOSMOKING禁烟贴纸车内请勿吸烟提示标志贴', 'http://117.50.74.117:8080/image/goods/pic08.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('9', '3', 'mxXXtfWfMFhXxzj0', '9', '28.8', '专用于朗动 内饰闪光膜碳纤贴膜 改装贴车内贴纸 中控碳纤维贴纸', 'http://117.50.74.117:8080/image/goods/pic09.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('10', '2', '2cINFP6VlaqI3NiX', '10', '78.8', '十代思域改装专用装饰内饰贴纸16款本田新思域中控改色碳纤维贴膜 ', 'http://117.50.74.117:8080/image/goods/pic10.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('11', '2', 'kGSYm9tzotwgfoke', '11', '21.8', '汽车内饰贴纸车门改色装饰膜车身贴划痕个性遮挡改装钓箱贴画防水', 'http://117.50.74.117:8080/image/goods/pic11.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('12', '2', 'VwuHrQJLmNlwIYj4', '12', '11.8', '汽车碳纤维贴膜碳纤维贴纸整车贴碳纤维布内饰装饰全车贴汽车贴膜', 'http://117.50.74.117:8080/image/goods/pic12.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('13', '2', 'iS1VTZG3Aui4CJRV', '13', '62.8', '北京现代朗动领动改装车贴花专用雨刷器贴纸装饰雨刮个性创意内饰', 'http://117.50.74.117:8080/image/goods/pic13.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('14', '2', '3cuOTCegOA7T4lv1', '14', '12.1', '汽车用品蜡拖除尘掸子擦车拖把洗车帮手软毛刷车刷子清洁工具专用', 'http://117.50.74.117:8080/image/goods/pic14.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('15', '3', 'DqsQR9Kqt10YjnEE', '15', '19.1', '洗车拖把专用刷车用刷子长柄伸缩式非纯棉多功能软毛汽车擦车工具', 'http://117.50.74.117:8080/image/ad/pic01.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('16', '1', 'cwb9Sq4XS8bfbgU1', '16', '199.1', '儿童安全座椅汽车用婴儿宝宝车载9个月-12岁0-4档简易便携3isofix', 'http://localhost:8080/storage/fetch/7QUx56LkEfB7BDjordSr.png', '456', '0');
INSERT INTO `tbl_goods_ad` VALUES ('17', '1', '9vknw70YqxsOJp6h', '17', '59.9', '车载HUD高清抬头显示器汽车通用行车电脑OBD平视速度多功能投影仪', 'http://117.50.74.117:8080/image/ad/pic03.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('18', '1', 'aeMr60KLzJmsAnI3', '18', '99.9', '汽车车载HUD抬头显示器 OBD车速水温投影仪 途驰安A100高清通用', 'http://117.50.74.117:8080/image/ad/pic04.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('19', '1', 'qobHsAwFtbNQ3Gx2', '19', '999.9', '新途观L迈腾奥德赛智能车机导航仪车载中控流媒体倒车影像一体机', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('20', '1', 'qobHsAwFtbNQ3Gx2', null, '999.9', '新途观L迈腾奥德赛智能车机导航仪车载中控流媒体倒车影像一体机', 'http://localhost:8080/storage/fetch/A9SPN2pr4QU4sFWBXW6o.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('21', '1', 'qobHsAwFtbNQ3Gx2', null, '999.9', '新途观L迈腾奥德赛智能车机导航仪车载中控流媒体倒车影像一体机', 'http://localhost:8080/storage/fetch/McYuriVgkVcWPvYOEfT9.png', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('22', '1', 'qobHsAwFtbNQ3Gx2', null, '999.9', '新途观L迈腾奥德赛智能车机导航仪车载中控流媒体倒车影像一体机', 'http://localhost:8080/storage/fetch/9rj9OLJVzGmS0B90UVdk.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('23', '1', 'cwb9Sq4XS8bfbgU1', null, '199.1', '儿童安全座椅汽车用婴儿宝宝车载9个月-12岁0-4档简易便携3isofix', 'http://localhost:8080/storage/fetch/rVul6qZYo1IdUdOUDQwp.jpg', null, '1');
INSERT INTO `tbl_goods_ad` VALUES ('24', '1', 'cwb9Sq4XS8bfbgU1', null, '199.1', '儿童安全座椅汽车用婴儿宝宝车载9个月-12岁0-4档简易便携3isofix', 'http://localhost:8080/storage/fetch/2IA8GAh18zfxa2cfqgt6.jpg', null, '1');

-- ----------------------------
-- Table structure for tbl_goods_list
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_list`;
CREATE TABLE `tbl_goods_list` (
  `glist_id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `g_flag` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `ord_id` int(11) DEFAULT NULL,
  `size_id` int(11) DEFAULT NULL,
  `size_text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`glist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_goods_list
-- ----------------------------
INSERT INTO `tbl_goods_list` VALUES ('1', '2', '1539703698523', '1122334455667788', null, '1', '63', '标准');
INSERT INTO `tbl_goods_list` VALUES ('2', '2', '1539703698553', '1122334455667788', null, '1', '62', '标准');

-- ----------------------------
-- Table structure for tbl_goods_size
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_size`;
CREATE TABLE `tbl_goods_size` (
  `gs_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `g_flag` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_goods_size
-- ----------------------------
INSERT INTO `tbl_goods_size` VALUES ('1', '1539225223663', 'DZPV4MSPycbRfSwS', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic04.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('2', '1539225223706', 'z8Wb3haAhnSvnzIq', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic01.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('3', '1539225223711', 'jpbb9oclUGxflXLy', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic02.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('4', '1539225223716', 'buYKApQnc8UziLYi', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic03.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('5', '1539225223720', 'nskmLoCFtlf5eWA2', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic05.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('6', '1539225223724', '4jbpTTJQPZD73FGx', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic06.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('7', '1539225223727', 'Xyx1AnbLbDUOlWP0', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic07.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('8', '1539225223731', 'QbDqc0Ubz35PdTSf', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic08.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('9', '1539225223734', 'mxXXtfWfMFhXxzj0', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic09.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('10', '1539225223738', '2cINFP6VlaqI3NiX', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic10.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('11', '1539225223741', 'kGSYm9tzotwgfoke', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic11.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('12', '1539225223748', 'VwuHrQJLmNlwIYj4', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic12.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('13', '1539225223751', 'iS1VTZG3Aui4CJRV', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic13.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('14', '1539225223754', '3cuOTCegOA7T4lv1', null, '1', '白色', 'http://117.50.74.117:8080/image/goods/pic14.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('15', '1539225223757', 'DqsQR9Kqt10YjnEE', null, '1', '白色', 'http://117.50.74.117:8080/image/ad/pic01.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('16', '1539225223761', 'cwb9Sq4XS8bfbgU1', null, '1', '白色', 'http://117.50.74.117:8080/image/ad/pic02.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('17', '1539225223765', '9vknw70YqxsOJp6h', null, '1', '白色', 'http://117.50.74.117:8080/image/ad/pic03.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('18', '1539225223768', 'aeMr60KLzJmsAnI3', null, '1', '白色', 'http://117.50.74.117:8080/image/ad/pic04.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('19', '1539225223771', 'qobHsAwFtbNQ3Gx2', null, '1', '白色', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('20', '1539225272798', 'DZPV4MSPycbRfSwS', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic04.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('21', '1539225272833', 'z8Wb3haAhnSvnzIq', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic01.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('22', '1539225272841', 'jpbb9oclUGxflXLy', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic02.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('23', '1539225272847', 'buYKApQnc8UziLYi', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic03.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('24', '1539225272855', 'nskmLoCFtlf5eWA2', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic05.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('25', '1539225272862', '4jbpTTJQPZD73FGx', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic06.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('26', '1539225272869', 'Xyx1AnbLbDUOlWP0', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic07.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('27', '1539225272876', 'QbDqc0Ubz35PdTSf', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic08.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('28', '1539225272884', 'mxXXtfWfMFhXxzj0', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic09.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('29', '1539225272891', '2cINFP6VlaqI3NiX', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic10.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('30', '1539225272898', 'kGSYm9tzotwgfoke', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic11.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('31', '1539225272905', 'VwuHrQJLmNlwIYj4', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic12.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('32', '1539225272911', 'iS1VTZG3Aui4CJRV', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic13.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('33', '1539225272918', '3cuOTCegOA7T4lv1', null, '1', '灰色', 'http://117.50.74.117:8080/image/goods/pic14.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('34', '1539225272925', 'DqsQR9Kqt10YjnEE', null, '1', '灰色', 'http://117.50.74.117:8080/image/ad/pic01.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('35', '1539225272932', 'cwb9Sq4XS8bfbgU1', null, '1', '灰色', 'http://117.50.74.117:8080/image/ad/pic02.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('36', '1539225272941', '9vknw70YqxsOJp6h', null, '1', '灰色', 'http://117.50.74.117:8080/image/ad/pic03.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('37', '1539225272948', 'aeMr60KLzJmsAnI3', null, '1', '灰色', 'http://117.50.74.117:8080/image/ad/pic04.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('38', '1539225272955', 'qobHsAwFtbNQ3Gx2', null, '1', '灰色', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('39', '1539225374241', 'DZPV4MSPycbRfSwS', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic04.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('40', '1539225374268', 'z8Wb3haAhnSvnzIq', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic01.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('41', '1539225374272', 'jpbb9oclUGxflXLy', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic02.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('42', '1539225374275', 'buYKApQnc8UziLYi', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic03.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('43', '1539225374278', 'nskmLoCFtlf5eWA2', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic05.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('44', '1539225374280', '4jbpTTJQPZD73FGx', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic06.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('45', '1539225374284', 'Xyx1AnbLbDUOlWP0', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic07.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('46', '1539225374288', 'QbDqc0Ubz35PdTSf', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic08.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('47', '1539225374291', 'mxXXtfWfMFhXxzj0', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic09.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('48', '1539225374293', '2cINFP6VlaqI3NiX', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic10.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('49', '1539225374296', 'kGSYm9tzotwgfoke', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic11.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('50', '1539225374299', 'VwuHrQJLmNlwIYj4', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic12.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('51', '1539225374302', 'iS1VTZG3Aui4CJRV', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic13.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('52', '1539225374305', '3cuOTCegOA7T4lv1', null, '1', '黑色', 'http://117.50.74.117:8080/image/goods/pic14.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('53', '1539225374308', 'DqsQR9Kqt10YjnEE', null, '1', '黑色', 'http://117.50.74.117:8080/image/ad/pic01.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('54', '1539225374312', 'cwb9Sq4XS8bfbgU1', null, '1', '黑色', 'http://117.50.74.117:8080/image/ad/pic02.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('55', '1539225374315', '9vknw70YqxsOJp6h', null, '1', '黑色', 'http://117.50.74.117:8080/image/ad/pic03.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('56', '1539225374318', 'aeMr60KLzJmsAnI3', null, '1', '黑色', 'http://117.50.74.117:8080/image/ad/pic04.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('57', '1539225374321', 'qobHsAwFtbNQ3Gx2', null, '1', '黑色', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('58', '1539701851273', '1008611001', null, '1', '标准', 'http://localhost:8080/storage/fetch/JQxJOi4lkDqdPt5dspKf.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('59', '1539701851279', '1008611001', null, '1', '标准', 'http://localhost:8080/storage/fetch/JQxJOi4lkDqdPt5dspKf.png', '1');
INSERT INTO `tbl_goods_size` VALUES ('60', '1539703040566', '1122334455667788', null, '1', '标准', 'http://localhost:8080/storage/fetch/gUaq263dCOnfsoZIDF0X.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('61', '1539703040572', '1122334455667788', null, '1', '标准', 'http://localhost:8080/storage/fetch/gUaq263dCOnfsoZIDF0X.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('62', '1539703435895', '12312312312sdasdas', null, '1', '标准', 'http://localhost:8080/storage/fetch/DURUExyp1ZxIXxlSdleM.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('63', '1539703520360', '12312312312sdasdas', null, '1', '标准', 'http://localhost:8080/storage/fetch/LfP6C13aJpEUh5Zjrw2B.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('64', '1539703756557', '12312312312sdasdasaaa', null, '1', '标准', 'http://localhost:8080/storage/fetch/LfP6C13aJpEUh5Zjrw2B.jpg', '1');
INSERT INTO `tbl_goods_size` VALUES ('65', '1539744405679', null, null, '1', '规格', '', '标准');
INSERT INTO `tbl_goods_size` VALUES ('66', '1539746158837', '11111111', null, '1', '123', 'http://localhost:8080/storage/fetch/m4m7a8lZtCmMEDdTSWzO.jpg', '123');

-- ----------------------------
-- Table structure for tbl_goods_text
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_text`;
CREATE TABLE `tbl_goods_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `text` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_goods_text
-- ----------------------------
INSERT INTO `tbl_goods_text` VALUES ('1', '1539701851261', '24', '<p>就是垫子，你懂的<img src=\"http://localhost:8080/storage/fetch/mAYL75AXjOiLWcor6kqB.png\" alt=\"图片呗\" width=\"700\" height=\"300\" /></p>\n<p>你看到了什么东西吗</p>');
INSERT INTO `tbl_goods_text` VALUES ('2', '1539703040563', '25', '<p>345689076543777</p>\n<p><img src=\"http://localhost:8080/storage/fetch/esEV7S3V5yac3sSVPHOG.jpg\" alt=\"图呗\" width=\"300\" height=\"300\" /></p>\n<p>我图文编辑看</p>');
INSERT INTO `tbl_goods_text` VALUES ('3', '1539703423861', '26', '<p>1</p>');
INSERT INTO `tbl_goods_text` VALUES ('4', '1539703510819', '27', '<p>1</p>');
INSERT INTO `tbl_goods_text` VALUES ('5', '1539703756555', '28', '<p>1</p>');
INSERT INTO `tbl_goods_text` VALUES ('6', '1539744405674', '29', '<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n<tbody>\n<tr style=\"height: 18px;\">\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234324</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n</tr>\n<tr style=\"height: 18px;\">\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n</tr>\n<tr style=\"height: 18px;\">\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">34</td>\n<td style=\"width: 16.6667%; height: 18px;\">234234</td>\n<td style=\"width: 16.6667%; height: 18px;\">32</td>\n</tr>\n<tr style=\"height: 18px;\">\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">324</td>\n</tr>\n<tr style=\"height: 18px;\">\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234234234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n<td style=\"width: 16.6667%; height: 18px;\">234</td>\n</tr>\n</tbody>\n</table>');
INSERT INTO `tbl_goods_text` VALUES ('7', '1539746158837', '30', '<p>123图文集合</p>');

-- ----------------------------
-- Table structure for tbl_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_type`;
CREATE TABLE `tbl_goods_type` (
  `gt_id` int(11) NOT NULL AUTO_INCREMENT,
  `icon_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_goods_type
-- ----------------------------
INSERT INTO `tbl_goods_type` VALUES ('1', null, '汽车内饰', null, '0', '1', '1', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('2', null, '美容养护', null, '0', '1', '2', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('3', null, '安全自驾', null, '0', '1', '3', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('4', null, '灯光升级', null, '0', '1', '4', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('5', 'http://117.50.74.117:8080/image/goodstype/type05.png', '坐垫', null, '1', '1', '5', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('6', 'http://117.50.74.117:8080/image/goodstype/type06.png', '脚垫', null, '1', '1', '6', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('7', 'http://117.50.74.117:8080/image/goodstype/type07.png', '抱枕', null, '1', '1', '7', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('8', 'http://117.50.74.117:8080/image/goodstype/type08.png', '腰靠', null, '1', '1', '8', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('9', 'http://117.50.74.117:8080/image/goodstype/type09.png', '挂坠', null, '1', '1', '9', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('10', 'http://117.50.74.117:8080/image/goodstype/type10.png', '车蜡', null, '2', '1', '10', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('11', 'http://117.50.74.117:8080/image/goodstype/type11.png', '清洁剂', null, '2', '1', '11', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('12', 'http://117.50.74.117:8080/image/goodstype/type12.png', '玻璃水', null, '2', '1', '12', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('13', 'http://117.50.74.117:8080/image/goodstype/type13.png', '防雨剂', null, '2', '1', '13', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('14', 'http://117.50.74.117:8080/image/goodstype/type14.png', '贴纸', null, '2', '1', '14', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('15', 'http://117.50.74.117:8080/image/goodstype/type15.png', '安全座椅', null, '3', '1', '15', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('16', 'http://117.50.74.117:8080/image/goodstype/type16.png', '汽修工具', null, '3', '1', '16', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('17', 'http://117.50.74.117:8080/image/goodstype/type17.png', '充气泵', null, '3', '1', '17', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('18', 'http://117.50.74.117:8080/image/goodstype/type18.png', '灭火器', null, '3', '1', '18', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('19', 'http://117.50.74.117:8080/image/goodstype/type19.png', '千斤顶', null, '3', '1', '19', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('20', 'http://117.50.74.117:8080/image/goodstype/type20.png', '定位器', null, '3', '1', '20', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('21', 'http://117.50.74.117:8080/image/goodstype/type21.png', 'LED大灯', null, '4', '1', '25', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('22', 'http://117.50.74.117:8080/image/goodstype/type22.png', '卤素灯', null, '4', '1', '25', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('23', 'http://117.50.74.117:8080/image/goodstype/type23.png', '氙气灯', null, '4', '1', '23', '', '我是描述');
INSERT INTO `tbl_goods_type` VALUES ('24', 'http://117.50.74.117:8080/image/goodstype/type24.png', '氛围灯', null, '4', '1', '24', '', '我是描述');

-- ----------------------------
-- Table structure for tbl_img_url
-- ----------------------------
DROP TABLE IF EXISTS `tbl_img_url`;
CREATE TABLE `tbl_img_url` (
  `img_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `g_flag` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_img_url
-- ----------------------------
INSERT INTO `tbl_img_url` VALUES ('1', '1539673227828', 'DZPV4MSPycbRfSwS', null, '1', 'http://117.50.74.117:8080/image/goods/pic04.png');
INSERT INTO `tbl_img_url` VALUES ('2', '1539673227865', 'z8Wb3haAhnSvnzIq', null, '1', 'http://117.50.74.117:8080/image/goods/pic01.png');
INSERT INTO `tbl_img_url` VALUES ('3', '1539673227869', 'jpbb9oclUGxflXLy', null, '1', 'http://117.50.74.117:8080/image/goods/pic02.png');
INSERT INTO `tbl_img_url` VALUES ('4', '1539673227873', 'buYKApQnc8UziLYi', null, '1', 'http://117.50.74.117:8080/image/goods/pic03.png');
INSERT INTO `tbl_img_url` VALUES ('5', '1539673227878', 'nskmLoCFtlf5eWA2', null, '1', 'http://117.50.74.117:8080/image/goods/pic05.png');
INSERT INTO `tbl_img_url` VALUES ('6', '1539673227883', '4jbpTTJQPZD73FGx', null, '1', 'http://117.50.74.117:8080/image/goods/pic06.jpg');
INSERT INTO `tbl_img_url` VALUES ('7', '1539673227886', 'Xyx1AnbLbDUOlWP0', null, '1', 'http://117.50.74.117:8080/image/goods/pic07.jpg');
INSERT INTO `tbl_img_url` VALUES ('8', '1539673227891', 'QbDqc0Ubz35PdTSf', null, '1', 'http://117.50.74.117:8080/image/goods/pic08.jpg');
INSERT INTO `tbl_img_url` VALUES ('9', '1539673227894', 'mxXXtfWfMFhXxzj0', null, '1', 'http://117.50.74.117:8080/image/goods/pic09.jpg');
INSERT INTO `tbl_img_url` VALUES ('10', '1539673227898', '2cINFP6VlaqI3NiX', null, '1', 'http://117.50.74.117:8080/image/goods/pic10.jpg');
INSERT INTO `tbl_img_url` VALUES ('11', '1539673227902', 'kGSYm9tzotwgfoke', null, '1', 'http://117.50.74.117:8080/image/goods/pic11.jpg');
INSERT INTO `tbl_img_url` VALUES ('12', '1539673227907', 'VwuHrQJLmNlwIYj4', null, '1', 'http://117.50.74.117:8080/image/goods/pic12.jpg');
INSERT INTO `tbl_img_url` VALUES ('13', '1539673227911', 'iS1VTZG3Aui4CJRV', null, '1', 'http://117.50.74.117:8080/image/goods/pic13.jpg');
INSERT INTO `tbl_img_url` VALUES ('14', '1539673227915', '3cuOTCegOA7T4lv1', null, '1', 'http://117.50.74.117:8080/image/goods/pic14.jpg');
INSERT INTO `tbl_img_url` VALUES ('15', '1539673227920', 'DqsQR9Kqt10YjnEE', null, '1', 'http://117.50.74.117:8080/image/ad/pic01.png');
INSERT INTO `tbl_img_url` VALUES ('16', '1539673227924', 'cwb9Sq4XS8bfbgU1', null, '1', 'http://117.50.74.117:8080/image/ad/pic02.png');
INSERT INTO `tbl_img_url` VALUES ('17', '1539673227928', '9vknw70YqxsOJp6h', null, '1', 'http://117.50.74.117:8080/image/ad/pic03.png');
INSERT INTO `tbl_img_url` VALUES ('18', '1539673227933', 'aeMr60KLzJmsAnI3', null, '1', 'http://117.50.74.117:8080/image/ad/pic04.png');
INSERT INTO `tbl_img_url` VALUES ('19', '1539673227937', 'qobHsAwFtbNQ3Gx2', null, '1', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg');
INSERT INTO `tbl_img_url` VALUES ('20', '1539676712594', 'DZPV4MSPycbRfSwS', null, '1', 'http://117.50.74.117:8080/image/goods/pic04.png');
INSERT INTO `tbl_img_url` VALUES ('21', '1539676712634', 'z8Wb3haAhnSvnzIq', null, '1', 'http://117.50.74.117:8080/image/goods/pic01.png');
INSERT INTO `tbl_img_url` VALUES ('22', '1539676712639', 'jpbb9oclUGxflXLy', null, '1', 'http://117.50.74.117:8080/image/goods/pic02.png');
INSERT INTO `tbl_img_url` VALUES ('23', '1539676712643', 'buYKApQnc8UziLYi', null, '1', 'http://117.50.74.117:8080/image/goods/pic03.png');
INSERT INTO `tbl_img_url` VALUES ('24', '1539676712647', 'nskmLoCFtlf5eWA2', null, '1', 'http://117.50.74.117:8080/image/goods/pic05.png');
INSERT INTO `tbl_img_url` VALUES ('25', '1539676712651', '4jbpTTJQPZD73FGx', null, '1', 'http://117.50.74.117:8080/image/goods/pic06.jpg');
INSERT INTO `tbl_img_url` VALUES ('26', '1539676712655', 'Xyx1AnbLbDUOlWP0', null, '1', 'http://117.50.74.117:8080/image/goods/pic07.jpg');
INSERT INTO `tbl_img_url` VALUES ('27', '1539676712659', 'QbDqc0Ubz35PdTSf', null, '1', 'http://117.50.74.117:8080/image/goods/pic08.jpg');
INSERT INTO `tbl_img_url` VALUES ('28', '1539676712662', 'mxXXtfWfMFhXxzj0', null, '1', 'http://117.50.74.117:8080/image/goods/pic09.jpg');
INSERT INTO `tbl_img_url` VALUES ('29', '1539676712666', '2cINFP6VlaqI3NiX', null, '1', 'http://117.50.74.117:8080/image/goods/pic10.jpg');
INSERT INTO `tbl_img_url` VALUES ('30', '1539676712670', 'kGSYm9tzotwgfoke', null, '1', 'http://117.50.74.117:8080/image/goods/pic11.jpg');
INSERT INTO `tbl_img_url` VALUES ('31', '1539676712675', 'VwuHrQJLmNlwIYj4', null, '1', 'http://117.50.74.117:8080/image/goods/pic12.jpg');
INSERT INTO `tbl_img_url` VALUES ('32', '1539676712678', 'iS1VTZG3Aui4CJRV', null, '1', 'http://117.50.74.117:8080/image/goods/pic13.jpg');
INSERT INTO `tbl_img_url` VALUES ('33', '1539676712682', '3cuOTCegOA7T4lv1', null, '1', 'http://117.50.74.117:8080/image/goods/pic14.jpg');
INSERT INTO `tbl_img_url` VALUES ('34', '1539676712686', 'DqsQR9Kqt10YjnEE', null, '1', 'http://117.50.74.117:8080/image/ad/pic01.png');
INSERT INTO `tbl_img_url` VALUES ('35', '1539676712690', 'cwb9Sq4XS8bfbgU1', null, '1', 'http://117.50.74.117:8080/image/ad/pic02.png');
INSERT INTO `tbl_img_url` VALUES ('36', '1539676712694', '9vknw70YqxsOJp6h', null, '1', 'http://117.50.74.117:8080/image/ad/pic03.png');
INSERT INTO `tbl_img_url` VALUES ('37', '1539676712698', 'aeMr60KLzJmsAnI3', null, '1', 'http://117.50.74.117:8080/image/ad/pic04.png');
INSERT INTO `tbl_img_url` VALUES ('38', '1539676712701', 'qobHsAwFtbNQ3Gx2', null, '1', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3164585476/TB2Y_TLJ29TBuNjy1zbXXXpepXa_!!3164585476.jpg_580x580Q90.jpg');
INSERT INTO `tbl_img_url` VALUES ('53', '1539701851249', '1008611001', null, '1', 'http://localhost:8080/storage/fetch/7XUhle6HZQpNueoYpMCR.png');
INSERT INTO `tbl_img_url` VALUES ('54', '1539701851256', '1008611001', null, '1', 'http://localhost:8080/storage/fetch/KJYBGlTlcmu1BNsJXUjo.png');
INSERT INTO `tbl_img_url` VALUES ('55', '1539701851257', '1008611001', null, '1', 'http://localhost:8080/storage/fetch/wD5dTe4pW3XsynixOthe.gif');
INSERT INTO `tbl_img_url` VALUES ('56', '1539701851259', '1008611001', null, '1', 'http://localhost:8080/storage/fetch/sZOJewUyis3BgjyyPaEq.jpg');
INSERT INTO `tbl_img_url` VALUES ('57', '1539701851260', '1008611001', null, '1', 'http://localhost:8080/storage/fetch/gDHXey2V9UBpD0F8MHWU.png');
INSERT INTO `tbl_img_url` VALUES ('58', '1539703040556', '1122334455667788', null, '1', 'http://localhost:8080/storage/fetch/hp642udxtE4pFwrQ6FTB.jpg');
INSERT INTO `tbl_img_url` VALUES ('59', '1539703040561', '1122334455667788', null, '1', 'http://localhost:8080/storage/fetch/SgBPocjGUC6nd8zxlc5z.jpg');
INSERT INTO `tbl_img_url` VALUES ('60', '1539703040561', '1122334455667788', null, '1', 'http://localhost:8080/storage/fetch/sh5I7IhzMcXf9npNMEZv.png');
INSERT INTO `tbl_img_url` VALUES ('61', '1539703040562', '1122334455667788', null, '1', 'http://localhost:8080/storage/fetch/KOAYGI4LHTuevf5BKkW0.jpg');
INSERT INTO `tbl_img_url` VALUES ('62', '1539703423859', '12312312312sdasdas', null, '1', 'http://localhost:8080/storage/fetch/eOnATvcJt1GKipQSm0Vo.png');
INSERT INTO `tbl_img_url` VALUES ('63', '1539703510814', '12312312312sdasdas', null, '1', 'http://localhost:8080/storage/fetch/eOnATvcJt1GKipQSm0Vo.png');
INSERT INTO `tbl_img_url` VALUES ('64', '1539703756553', '12312312312sdasdasaaa', null, '1', 'http://localhost:8080/storage/fetch/eOnATvcJt1GKipQSm0Vo.png');
INSERT INTO `tbl_img_url` VALUES ('65', '1539746158829', '11111111', null, '1', 'http://localhost:8080/storage/fetch/YwxDofEHoW9Qx7q2HLxp.jpg');
INSERT INTO `tbl_img_url` VALUES ('66', '1539746158836', '11111111', null, '1', 'http://localhost:8080/storage/fetch/hJVrdI5fo4QBFiBDV2AG.png');

-- ----------------------------
-- Table structure for tbl_key_word
-- ----------------------------
DROP TABLE IF EXISTS `tbl_key_word`;
CREATE TABLE `tbl_key_word` (
  `k_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`k_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_key_word
-- ----------------------------
INSERT INTO `tbl_key_word` VALUES ('1', '1539251862583', '关键字1', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('2', '1539251873932', '5', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('3', '1539251894553', '无', null, null, '2');
INSERT INTO `tbl_key_word` VALUES ('4', '1539251910376', '无法', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('5', '1539252809660', '1', null, null, '9');
INSERT INTO `tbl_key_word` VALUES ('6', '1539252813440', '13', null, null, '3');
INSERT INTO `tbl_key_word` VALUES ('7', '1539252816160', '12', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('8', '1539252817881', '14', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('9', '1539252973714', '46', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('10', '1539253334401', '23', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('11', '1539253515291', '123', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('12', '1539255594097', '车', null, null, '2');
INSERT INTO `tbl_key_word` VALUES ('13', '1539255777080', '的', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('14', '1539255795241', '滴的', null, null, '1');
INSERT INTO `tbl_key_word` VALUES ('15', '1539255904046', '就', null, null, '1');

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
  `ord_id` int(11) NOT NULL AUTO_INCREMENT,
  `actual_price` double DEFAULT NULL,
  `addr_id` int(11) DEFAULT NULL,
  `buy_msg` varchar(255) DEFAULT NULL,
  `confirm_time` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `freight_price` double DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `ord_sn` varchar(255) DEFAULT NULL,
  `order_price` double DEFAULT NULL,
  `pay_id` int(11) DEFAULT NULL,
  `pay_status` int(11) DEFAULT NULL,
  `pay_time` bigint(20) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ord_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
INSERT INTO `tbl_order` VALUES ('1', null, '1', null, null, '4', '1539703698509', null, '752', '1539703698509959774', '752', null, '0', null, '1', '球球一号店', '0', '1');

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES ('1', '可以操作一切', '管理员', '1', null);

-- ----------------------------
-- Table structure for tbl_shop
-- ----------------------------
DROP TABLE IF EXISTS `tbl_shop`;
CREATE TABLE `tbl_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_shop
-- ----------------------------
INSERT INTO `tbl_shop` VALUES ('1', '1534769690229', '球球一号店', '', '123', '1', 'QvByw1yn67wgbwOI', '123');
INSERT INTO `tbl_shop` VALUES ('2', '1534769726398', '球球二号店', '', '123123', '1', 'zH2tQbPFBjKHbxbM', '123123');

-- ----------------------------
-- Table structure for tbl_shop_car
-- ----------------------------
DROP TABLE IF EXISTS `tbl_shop_car`;
CREATE TABLE `tbl_shop_car` (
  `glist_id` int(11) NOT NULL AUTO_INCREMENT,
  `change_time` bigint(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `g_flag` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `size_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`glist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_shop_car
-- ----------------------------
INSERT INTO `tbl_shop_car` VALUES ('1', null, '1', '1539241556523', 'nskmLoCFtlf5eWA2', null, '2', '43', '1');
INSERT INTO `tbl_shop_car` VALUES ('2', null, '1', '1539689740941', 'DZPV4MSPycbRfSwS', null, '2', '1', '1');
INSERT INTO `tbl_shop_car` VALUES ('5', null, '3', '1539704105535', '1122334455667788', null, '1', '60', '1');

-- ----------------------------
-- Table structure for tbl_storage
-- ----------------------------
DROP TABLE IF EXISTS `tbl_storage`;
CREATE TABLE `tbl_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `add_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_storage
-- ----------------------------
INSERT INTO `tbl_storage` VALUES ('1', '2018-10-16 09:47:53', null, 'FsevaWwIOpdSesx41ryX.png', '2018-10-16 09:47:53', 'whjy.png', '3665', 'image/png', 'nullFsevaWwIOpdSesx41ryX.png', null);
INSERT INTO `tbl_storage` VALUES ('2', '2018-10-16 09:48:32', null, 'wBAY6kvv0dNQ6sJCfJEw.png', '2018-10-16 09:48:32', 'report.png', '9318', 'image/png', 'nullwBAY6kvv0dNQ6sJCfJEw.png', null);
INSERT INTO `tbl_storage` VALUES ('3', '2018-10-16 09:54:12', null, 'dPgRrkORTUvMMXc0cl0B.png', '2018-10-16 09:54:12', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/wxshop/storage/fetch/dPgRrkORTUvMMXc0cl0B.png', null);
INSERT INTO `tbl_storage` VALUES ('4', '2018-10-16 10:00:05', null, 'nCR1Bjf3O9fXJgoU9L1p.png', '2018-10-16 10:00:05', 'whjy.png', '3665', 'image/png', 'http://localhost:80/wxshop/image/nCR1Bjf3O9fXJgoU9L1p.png', null);
INSERT INTO `tbl_storage` VALUES ('5', '2018-10-16 10:09:15', null, 'QSRQDeZZ14LTJQ75MTdH.png', '2018-10-16 10:09:15', 'whjy.png', '3665', 'image/png', 'http://localhost:80/storage/fetch/QSRQDeZZ14LTJQ75MTdH.png', null);
INSERT INTO `tbl_storage` VALUES ('6', '2018-10-16 10:09:46', null, 'v0ew7pcmFKQ2l6tfb1Dn.png', '2018-10-16 10:09:46', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/v0ew7pcmFKQ2l6tfb1Dn.png', null);
INSERT INTO `tbl_storage` VALUES ('7', '2018-10-16 10:11:10', null, '0B3VoGbzOIYd66c0tc33.png', '2018-10-16 10:11:10', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/0B3VoGbzOIYd66c0tc33.png', null);
INSERT INTO `tbl_storage` VALUES ('8', '2018-10-16 10:31:04', null, 'CJJYMGXRK2K4ipKmXOqc.gif', '2018-10-16 10:31:04', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/CJJYMGXRK2K4ipKmXOqc.gif', null);
INSERT INTO `tbl_storage` VALUES ('9', '2018-10-16 10:38:57', null, 'aNx8Nfreq3tkrat63ofA.png', '2018-10-16 10:38:57', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/aNx8Nfreq3tkrat63ofA.png', null);
INSERT INTO `tbl_storage` VALUES ('10', '2018-10-16 10:42:56', null, 'RRYSkCqwC8NsP9oDXxaz.png', '2018-10-16 10:42:56', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/RRYSkCqwC8NsP9oDXxaz.png', null);
INSERT INTO `tbl_storage` VALUES ('11', '2018-10-16 10:53:31', null, 'YQRFomR7NE378AwaWpvh.png', '2018-10-16 10:53:31', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/YQRFomR7NE378AwaWpvh.png', null);
INSERT INTO `tbl_storage` VALUES ('12', '2018-10-16 10:53:39', null, 'NBL9MzieW46JAvMzihxI.png', '2018-10-16 10:53:39', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/NBL9MzieW46JAvMzihxI.png', null);
INSERT INTO `tbl_storage` VALUES ('13', '2018-10-16 11:00:54', null, 'W73WQHr4cs6WSYXBQVsx.gif', '2018-10-16 11:00:54', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/W73WQHr4cs6WSYXBQVsx.gif', null);
INSERT INTO `tbl_storage` VALUES ('14', '2018-10-16 11:10:31', null, '9jgGqQbgyQAnXTm4ylAE.png', '2018-10-16 11:10:31', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/9jgGqQbgyQAnXTm4ylAE.png', null);
INSERT INTO `tbl_storage` VALUES ('15', '2018-10-16 16:16:57', null, '5P8vCZ6apWPvtDeUdSZ8.png', '2018-10-16 16:16:57', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/5P8vCZ6apWPvtDeUdSZ8.png', null);
INSERT INTO `tbl_storage` VALUES ('16', '2018-10-16 16:20:08', null, 'A8mxnbKl4FHTSZAgjc3H.png', '2018-10-16 16:20:08', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/A8mxnbKl4FHTSZAgjc3H.png', null);
INSERT INTO `tbl_storage` VALUES ('17', '2018-10-16 16:21:55', null, 'f3FAqWYcqHI0zjneHjZq.png', '2018-10-16 16:21:55', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/f3FAqWYcqHI0zjneHjZq.png', null);
INSERT INTO `tbl_storage` VALUES ('18', '2018-10-16 16:22:02', null, '2WCFRFk6oAiHEuhj3uOm.png', '2018-10-16 16:22:02', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/2WCFRFk6oAiHEuhj3uOm.png', null);
INSERT INTO `tbl_storage` VALUES ('19', '2018-10-16 16:22:05', null, 'vO4hweadrrHIrF5kFOPz.gif', '2018-10-16 16:22:05', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/vO4hweadrrHIrF5kFOPz.gif', null);
INSERT INTO `tbl_storage` VALUES ('20', '2018-10-16 16:22:07', null, 'CXCXUcQH2f620zQf678E.png', '2018-10-16 16:22:07', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/CXCXUcQH2f620zQf678E.png', null);
INSERT INTO `tbl_storage` VALUES ('21', '2018-10-16 16:22:10', null, 'B7uaYLHjgm77p45kbizb.png', '2018-10-16 16:22:10', 'report.png', '9318', 'image/png', 'http://localhost:8080/storage/fetch/B7uaYLHjgm77p45kbizb.png', null);
INSERT INTO `tbl_storage` VALUES ('22', '2018-10-16 16:22:13', null, 'bR2ZoLYTmkm8bf4gHxjs.png', '2018-10-16 16:22:13', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/bR2ZoLYTmkm8bf4gHxjs.png', null);
INSERT INTO `tbl_storage` VALUES ('23', '2018-10-16 16:23:06', null, 'PVz6FAw0rec8rrF42TLV.jpg', '2018-10-16 16:23:06', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/PVz6FAw0rec8rrF42TLV.jpg', null);
INSERT INTO `tbl_storage` VALUES ('24', '2018-10-16 16:24:07', null, 'oUmHyjpKcZOrJcI2VUXJ.png', '2018-10-16 16:24:07', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/oUmHyjpKcZOrJcI2VUXJ.png', null);
INSERT INTO `tbl_storage` VALUES ('25', '2018-10-16 16:24:46', null, 'zKiE11072F38X7ixJbnm.png', '2018-10-16 16:24:46', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/zKiE11072F38X7ixJbnm.png', null);
INSERT INTO `tbl_storage` VALUES ('26', '2018-10-16 16:24:50', null, 'F9fD6VZAW92ADsHPuN6y.jpg', '2018-10-16 16:24:50', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/F9fD6VZAW92ADsHPuN6y.jpg', null);
INSERT INTO `tbl_storage` VALUES ('27', '2018-10-16 16:24:54', null, 'qYwV77VTnM7C9WrAShko.png', '2018-10-16 16:24:54', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/qYwV77VTnM7C9WrAShko.png', null);
INSERT INTO `tbl_storage` VALUES ('28', '2018-10-16 16:24:57', null, 'szwz4lSPJc6k7qo8oA0d.gif', '2018-10-16 16:24:57', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/szwz4lSPJc6k7qo8oA0d.gif', null);
INSERT INTO `tbl_storage` VALUES ('29', '2018-10-16 16:25:03', null, 'CdqMnMvgLavdQ8pVGz8Y.gif', '2018-10-16 16:25:03', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/CdqMnMvgLavdQ8pVGz8Y.gif', null);
INSERT INTO `tbl_storage` VALUES ('30', '2018-10-16 16:25:06', null, 'cTCdN2tjz0nWtKUT0HOh.png', '2018-10-16 16:25:06', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/cTCdN2tjz0nWtKUT0HOh.png', null);
INSERT INTO `tbl_storage` VALUES ('31', '2018-10-16 16:25:09', null, 'vbM8VPiBcavbWliD3y2a.png', '2018-10-16 16:25:09', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/vbM8VPiBcavbWliD3y2a.png', null);
INSERT INTO `tbl_storage` VALUES ('32', '2018-10-16 16:25:12', null, 'AxYnowh81mWFTFPDxuIj.png', '2018-10-16 16:25:12', 'report.png', '9318', 'image/png', 'http://localhost:8080/storage/fetch/AxYnowh81mWFTFPDxuIj.png', null);
INSERT INTO `tbl_storage` VALUES ('33', '2018-10-16 16:25:22', null, 'mglu4bXystiLBGyGxV1U.jpg', '2018-10-16 16:25:22', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/mglu4bXystiLBGyGxV1U.jpg', null);
INSERT INTO `tbl_storage` VALUES ('34', '2018-10-16 16:40:48', null, 'xfQXzgRgF0ifCHxshgS0.png', '2018-10-16 16:40:48', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/xfQXzgRgF0ifCHxshgS0.png', null);
INSERT INTO `tbl_storage` VALUES ('35', '2018-10-16 17:52:25', null, '52bJ9KlPif9K15CNoaoy.png', '2018-10-16 17:52:25', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/52bJ9KlPif9K15CNoaoy.png', null);
INSERT INTO `tbl_storage` VALUES ('36', '2018-10-16 17:53:49', null, 'vGTaNOeJvJUedvWqyqLe.png', '2018-10-16 17:53:49', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/vGTaNOeJvJUedvWqyqLe.png', null);
INSERT INTO `tbl_storage` VALUES ('37', '2018-10-16 17:53:51', null, '0VPuqHAT0KuCkNAUNmML.jpg', '2018-10-16 17:53:51', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/0VPuqHAT0KuCkNAUNmML.jpg', null);
INSERT INTO `tbl_storage` VALUES ('38', '2018-10-16 17:53:54', null, 'OIEM6paNXdd1veWfEtuh.jpg', '2018-10-16 17:53:54', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/OIEM6paNXdd1veWfEtuh.jpg', null);
INSERT INTO `tbl_storage` VALUES ('39', '2018-10-16 17:53:59', null, 'p8lzc3OCGNaVC4YwqIrI.gif', '2018-10-16 17:53:59', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/p8lzc3OCGNaVC4YwqIrI.gif', null);
INSERT INTO `tbl_storage` VALUES ('40', '2018-10-16 17:54:05', null, 'Qlt5ceMYLUGS5GoWCeaj.jpg', '2018-10-16 17:54:05', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/Qlt5ceMYLUGS5GoWCeaj.jpg', null);
INSERT INTO `tbl_storage` VALUES ('41', '2018-10-16 17:55:03', null, 'MQmmz5174blV8JXdeyxv.jpg', '2018-10-16 17:55:03', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/MQmmz5174blV8JXdeyxv.jpg', null);
INSERT INTO `tbl_storage` VALUES ('42', '2018-10-16 17:55:25', null, 'zOD9bd2OTiD1tZnhuWeR.png', '2018-10-16 17:55:25', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/zOD9bd2OTiD1tZnhuWeR.png', null);
INSERT INTO `tbl_storage` VALUES ('43', '2018-10-16 17:55:41', null, 'dxRD6tBCsdqGR2fyROI5.png', '2018-10-16 17:55:41', 'report.png', '9318', 'image/png', 'http://localhost:8080/storage/fetch/dxRD6tBCsdqGR2fyROI5.png', null);
INSERT INTO `tbl_storage` VALUES ('44', '2018-10-16 17:55:56', null, 'xYn8xa4HSMJPO3ozIEAm.png', '2018-10-16 17:55:56', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/xYn8xa4HSMJPO3ozIEAm.png', null);
INSERT INTO `tbl_storage` VALUES ('45', '2018-10-16 17:56:12', null, 'xyrtCzb9wfiCLMBEW50g.png', '2018-10-16 17:56:12', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/xyrtCzb9wfiCLMBEW50g.png', null);
INSERT INTO `tbl_storage` VALUES ('46', '2018-10-16 17:56:49', null, 'ST9kTu7RLc67viQwBOiA.png', '2018-10-16 17:56:49', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/ST9kTu7RLc67viQwBOiA.png', null);
INSERT INTO `tbl_storage` VALUES ('47', '2018-10-16 17:58:13', null, 'EX02wk3RU5MvNPC6oovF.png', '2018-10-16 17:58:13', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/EX02wk3RU5MvNPC6oovF.png', null);
INSERT INTO `tbl_storage` VALUES ('48', '2018-10-16 18:02:12', null, '3Iqz7xumcm0W2tCvFfa8.png', '2018-10-16 18:02:12', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/3Iqz7xumcm0W2tCvFfa8.png', null);
INSERT INTO `tbl_storage` VALUES ('49', '2018-10-16 18:02:15', null, 'fy0o53n7i88rcSSbOeFu.png', '2018-10-16 18:02:15', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/fy0o53n7i88rcSSbOeFu.png', null);
INSERT INTO `tbl_storage` VALUES ('50', '2018-10-16 18:02:52', null, 'Q1xHp3eBUZScuMbXbI56.png', '2018-10-16 18:02:52', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/Q1xHp3eBUZScuMbXbI56.png', null);
INSERT INTO `tbl_storage` VALUES ('51', '2018-10-16 18:03:08', null, '0igxqiVuTA6HEf2ujlcV.png', '2018-10-16 18:03:08', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/0igxqiVuTA6HEf2ujlcV.png', null);
INSERT INTO `tbl_storage` VALUES ('52', '2018-10-16 18:04:20', null, 'yPkRlakYx3S7sIAWUXVy.jpg', '2018-10-16 18:04:20', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/yPkRlakYx3S7sIAWUXVy.jpg', null);
INSERT INTO `tbl_storage` VALUES ('53', '2018-10-16 18:04:48', null, 'oig66qOqHTDhZNgtv6Qq.png', '2018-10-16 18:04:48', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/oig66qOqHTDhZNgtv6Qq.png', null);
INSERT INTO `tbl_storage` VALUES ('54', '2018-10-16 18:05:04', null, '8mUc3GDKIxdanwtxOTqT.png', '2018-10-16 18:05:04', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/8mUc3GDKIxdanwtxOTqT.png', null);
INSERT INTO `tbl_storage` VALUES ('55', '2018-10-16 18:05:47', null, 'OiymcXjXndUWOaLZuBst.jpg', '2018-10-16 18:05:47', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/OiymcXjXndUWOaLZuBst.jpg', null);
INSERT INTO `tbl_storage` VALUES ('56', '2018-10-16 18:06:22', null, 'AUngYtiZCBPp2uR4HnRL.png', '2018-10-16 18:06:22', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/AUngYtiZCBPp2uR4HnRL.png', null);
INSERT INTO `tbl_storage` VALUES ('57', '2018-10-16 18:06:53', null, 'wQHaJbqPaeYDNSa7ihzj.jpg', '2018-10-16 18:06:53', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/wQHaJbqPaeYDNSa7ihzj.jpg', null);
INSERT INTO `tbl_storage` VALUES ('58', '2018-10-16 18:06:56', null, 'ac8ipznzwEZ4Ijay99mz.png', '2018-10-16 18:06:56', 'beian.png', '19256', 'image/png', 'http://localhost:8080/storage/fetch/ac8ipznzwEZ4Ijay99mz.png', null);
INSERT INTO `tbl_storage` VALUES ('59', '2018-10-16 18:06:58', null, 'ZGNUrYw4ndpxSkKKSdb1.png', '2018-10-16 18:06:58', 'report.png', '9318', 'image/png', 'http://localhost:8080/storage/fetch/ZGNUrYw4ndpxSkKKSdb1.png', null);
INSERT INTO `tbl_storage` VALUES ('60', '2018-10-16 18:07:00', null, 'n0Y1oxHSsp4rwFBqhBbi.gif', '2018-10-16 18:07:00', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/n0Y1oxHSsp4rwFBqhBbi.gif', null);
INSERT INTO `tbl_storage` VALUES ('61', '2018-10-16 18:18:59', null, 'ns9U4xtNvK6HFblXEBqp.jpg', '2018-10-16 18:18:59', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/ns9U4xtNvK6HFblXEBqp.jpg', null);
INSERT INTO `tbl_storage` VALUES ('62', '2018-10-16 18:26:31', null, 'HqNMgB71zFO018zmL1Na.png', '2018-10-16 18:26:31', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/HqNMgB71zFO018zmL1Na.png', null);
INSERT INTO `tbl_storage` VALUES ('63', '2018-10-16 18:26:41', null, 'rmXj6r01RYJ0lmADwAP8.jpg', '2018-10-16 18:26:41', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/rmXj6r01RYJ0lmADwAP8.jpg', null);
INSERT INTO `tbl_storage` VALUES ('64', '2018-10-16 18:26:47', null, 'VMBxTSit2QkZ5eSxHIiS.png', '2018-10-16 18:26:47', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/VMBxTSit2QkZ5eSxHIiS.png', null);
INSERT INTO `tbl_storage` VALUES ('65', '2018-10-16 21:41:25', null, 'LNoQPYm0EVdh9A0MqbbE.png', '2018-10-16 21:41:25', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/LNoQPYm0EVdh9A0MqbbE.png', null);
INSERT INTO `tbl_storage` VALUES ('66', '2018-10-16 21:43:30', null, 'P16fSEA85EeAjNCByfXI.png', '2018-10-16 21:43:30', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/P16fSEA85EeAjNCByfXI.png', null);
INSERT INTO `tbl_storage` VALUES ('67', '2018-10-16 21:43:39', null, 'JzHBeF8oVzTpDbzinrhD.gif', '2018-10-16 21:43:39', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/JzHBeF8oVzTpDbzinrhD.gif', null);
INSERT INTO `tbl_storage` VALUES ('68', '2018-10-16 21:43:51', null, 'bDlyiBSoGjLcxCAl4BuK.png', '2018-10-16 21:43:51', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/bDlyiBSoGjLcxCAl4BuK.png', null);
INSERT INTO `tbl_storage` VALUES ('69', '2018-10-16 21:44:12', null, 'YWYqdkm6qHx4ItzthOsm.png', '2018-10-16 21:44:12', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/YWYqdkm6qHx4ItzthOsm.png', null);
INSERT INTO `tbl_storage` VALUES ('70', '2018-10-16 21:44:24', null, 'sxewyQWHoUzVmwVSGjLD.png', '2018-10-16 21:44:24', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/sxewyQWHoUzVmwVSGjLD.png', null);
INSERT INTO `tbl_storage` VALUES ('71', '2018-10-16 21:45:07', null, 'ab297jU5jWG1aUouvCHl.png', '2018-10-16 21:45:07', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/ab297jU5jWG1aUouvCHl.png', null);
INSERT INTO `tbl_storage` VALUES ('72', '2018-10-16 21:45:23', null, '9h4VNiTVLZzIKj6yH0D2.png', '2018-10-16 21:45:23', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/9h4VNiTVLZzIKj6yH0D2.png', null);
INSERT INTO `tbl_storage` VALUES ('73', '2018-10-16 21:47:29', null, 'zX2ikpcjjZchOwPtvJMT.png', '2018-10-16 21:47:29', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/zX2ikpcjjZchOwPtvJMT.png', null);
INSERT INTO `tbl_storage` VALUES ('74', '2018-10-16 21:47:53', null, 'cfHQ0sESCrFAUjbbyxeM.png', '2018-10-16 21:47:53', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/cfHQ0sESCrFAUjbbyxeM.png', null);
INSERT INTO `tbl_storage` VALUES ('75', '2018-10-16 21:59:55', null, 'vIfrakjS1kLQMKVuqYLp.png', '2018-10-16 21:59:55', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/vIfrakjS1kLQMKVuqYLp.png', null);
INSERT INTO `tbl_storage` VALUES ('76', '2018-10-16 22:00:03', null, 'eMh93SMsEAXbeuKYyrbs.gif', '2018-10-16 22:00:03', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/eMh93SMsEAXbeuKYyrbs.gif', null);
INSERT INTO `tbl_storage` VALUES ('77', '2018-10-16 22:48:56', null, 'JQxJOi4lkDqdPt5dspKf.png', '2018-10-16 22:48:56', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/JQxJOi4lkDqdPt5dspKf.png', null);
INSERT INTO `tbl_storage` VALUES ('78', '2018-10-16 22:48:59', null, '7XUhle6HZQpNueoYpMCR.png', '2018-10-16 22:48:59', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/7XUhle6HZQpNueoYpMCR.png', null);
INSERT INTO `tbl_storage` VALUES ('79', '2018-10-16 22:49:02', null, 'KJYBGlTlcmu1BNsJXUjo.png', '2018-10-16 22:49:02', 'beian.png', '19256', 'image/png', 'http://localhost:8080/storage/fetch/KJYBGlTlcmu1BNsJXUjo.png', null);
INSERT INTO `tbl_storage` VALUES ('80', '2018-10-16 22:49:05', null, 'wD5dTe4pW3XsynixOthe.gif', '2018-10-16 22:49:05', 'shgs2.gif', '1973', 'image/gif', 'http://localhost:8080/storage/fetch/wD5dTe4pW3XsynixOthe.gif', null);
INSERT INTO `tbl_storage` VALUES ('81', '2018-10-16 22:49:10', null, 'sZOJewUyis3BgjyyPaEq.jpg', '2018-10-16 22:49:10', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/sZOJewUyis3BgjyyPaEq.jpg', null);
INSERT INTO `tbl_storage` VALUES ('82', '2018-10-16 22:49:18', null, 'gDHXey2V9UBpD0F8MHWU.png', '2018-10-16 22:49:18', 'jzjh.png', '9120', 'image/png', 'http://localhost:8080/storage/fetch/gDHXey2V9UBpD0F8MHWU.png', null);
INSERT INTO `tbl_storage` VALUES ('83', '2018-10-16 22:49:49', null, 'mAYL75AXjOiLWcor6kqB.png', '2018-10-16 22:49:49', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/mAYL75AXjOiLWcor6kqB.png', null);
INSERT INTO `tbl_storage` VALUES ('84', '2018-10-16 22:50:20', null, 'GS0Z7TSfQOqc9O0RSbxQ.png', '2018-10-16 22:50:20', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/GS0Z7TSfQOqc9O0RSbxQ.png', null);
INSERT INTO `tbl_storage` VALUES ('85', '2018-10-16 22:50:28', null, 'CzbCS8K6RITFgkaDqy2z.jpg', '2018-10-16 22:50:28', 'img_ne.jpg', '2666', 'image/jpeg', 'http://localhost:8080/storage/fetch/CzbCS8K6RITFgkaDqy2z.jpg', null);
INSERT INTO `tbl_storage` VALUES ('86', '2018-10-16 22:50:37', null, '4zR5gR4HCcgPucGzRm8X.png', '2018-10-16 22:50:37', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/4zR5gR4HCcgPucGzRm8X.png', null);
INSERT INTO `tbl_storage` VALUES ('87', '2018-10-16 23:15:23', null, 'SxfxWNvPFsi9pWRrBspf.png', '2018-10-16 23:15:23', 'whjy.png', '3665', 'image/png', 'http://localhost:8080/storage/fetch/SxfxWNvPFsi9pWRrBspf.png', null);
INSERT INTO `tbl_storage` VALUES ('88', '2018-10-16 23:15:26', null, 'WyjhSAJyQJI8VEoHJdTx.png', '2018-10-16 23:15:26', '14943128782426.png', '323967', 'image/png', 'http://localhost:8080/storage/fetch/WyjhSAJyQJI8VEoHJdTx.png', null);
INSERT INTO `tbl_storage` VALUES ('89', '2018-10-16 23:15:39', null, 'T8RzP7mMwGm8bs9v95Yz.jpg', '2018-10-16 23:15:39', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/T8RzP7mMwGm8bs9v95Yz.jpg', null);
INSERT INTO `tbl_storage` VALUES ('90', '2018-10-16 23:15:44', null, 'gUaq263dCOnfsoZIDF0X.jpg', '2018-10-16 23:15:44', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/gUaq263dCOnfsoZIDF0X.jpg', null);
INSERT INTO `tbl_storage` VALUES ('91', '2018-10-16 23:15:47', null, 'hp642udxtE4pFwrQ6FTB.jpg', '2018-10-16 23:15:47', '微信图片_20181010100742.jpg', '55231', 'image/jpeg', 'http://localhost:8080/storage/fetch/hp642udxtE4pFwrQ6FTB.jpg', null);
INSERT INTO `tbl_storage` VALUES ('92', '2018-10-16 23:15:50', null, 'SgBPocjGUC6nd8zxlc5z.jpg', '2018-10-16 23:15:50', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/SgBPocjGUC6nd8zxlc5z.jpg', null);
INSERT INTO `tbl_storage` VALUES ('93', '2018-10-16 23:15:52', null, 'sh5I7IhzMcXf9npNMEZv.png', '2018-10-16 23:15:52', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/sh5I7IhzMcXf9npNMEZv.png', null);
INSERT INTO `tbl_storage` VALUES ('94', '2018-10-16 23:15:57', null, 'KOAYGI4LHTuevf5BKkW0.jpg', '2018-10-16 23:15:57', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/KOAYGI4LHTuevf5BKkW0.jpg', null);
INSERT INTO `tbl_storage` VALUES ('95', '2018-10-16 23:16:33', null, 'esEV7S3V5yac3sSVPHOG.jpg', '2018-10-16 23:16:33', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/esEV7S3V5yac3sSVPHOG.jpg', null);
INSERT INTO `tbl_storage` VALUES ('96', '2018-10-16 23:17:03', null, 'bopye7nk7WB7eDbeCpgo.jpg', '2018-10-16 23:17:03', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/bopye7nk7WB7eDbeCpgo.jpg', null);
INSERT INTO `tbl_storage` VALUES ('97', '2018-10-16 23:17:10', null, 'EWjU1m5fhxZ1TeEYfRXr.jpg', '2018-10-16 23:17:10', '微信图片_20181010100742.jpg', '55231', 'image/jpeg', 'http://localhost:8080/storage/fetch/EWjU1m5fhxZ1TeEYfRXr.jpg', null);
INSERT INTO `tbl_storage` VALUES ('98', '2018-10-16 23:22:58', null, 'DURUExyp1ZxIXxlSdleM.jpg', '2018-10-16 23:22:58', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/DURUExyp1ZxIXxlSdleM.jpg', null);
INSERT INTO `tbl_storage` VALUES ('99', '2018-10-16 23:23:00', null, 'eOnATvcJt1GKipQSm0Vo.png', '2018-10-16 23:23:00', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/eOnATvcJt1GKipQSm0Vo.png', null);
INSERT INTO `tbl_storage` VALUES ('100', '2018-10-16 23:23:29', null, 'LfP6C13aJpEUh5Zjrw2B.jpg', '2018-10-16 23:23:29', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/LfP6C13aJpEUh5Zjrw2B.jpg', null);
INSERT INTO `tbl_storage` VALUES ('101', '2018-10-17 09:04:51', null, '8CfKU8sjxmTQCkwRpo5i.jpg', '2018-10-17 09:04:51', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/8CfKU8sjxmTQCkwRpo5i.jpg', null);
INSERT INTO `tbl_storage` VALUES ('102', '2018-10-17 09:06:01', null, 'Z76bkxTbUuQCbKjItKxp.jpg', '2018-10-17 09:06:01', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/Z76bkxTbUuQCbKjItKxp.jpg', null);
INSERT INTO `tbl_storage` VALUES ('103', '2018-10-17 09:11:24', null, 'SXeYybr62PBghjUPwI7o.jpg', '2018-10-17 09:11:24', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/SXeYybr62PBghjUPwI7o.jpg', null);
INSERT INTO `tbl_storage` VALUES ('104', '2018-10-17 09:42:23', null, 'A9SPN2pr4QU4sFWBXW6o.jpg', '2018-10-17 09:42:23', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/A9SPN2pr4QU4sFWBXW6o.jpg', null);
INSERT INTO `tbl_storage` VALUES ('105', '2018-10-17 09:49:46', null, 'McYuriVgkVcWPvYOEfT9.png', '2018-10-17 09:49:46', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/McYuriVgkVcWPvYOEfT9.png', null);
INSERT INTO `tbl_storage` VALUES ('106', '2018-10-17 09:53:19', null, 'aKftco6Lxcn6iRASgR1K.jpg', '2018-10-17 09:53:19', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/aKftco6Lxcn6iRASgR1K.jpg', null);
INSERT INTO `tbl_storage` VALUES ('107', '2018-10-17 09:55:29', null, '9rj9OLJVzGmS0B90UVdk.jpg', '2018-10-17 09:55:29', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/9rj9OLJVzGmS0B90UVdk.jpg', null);
INSERT INTO `tbl_storage` VALUES ('108', '2018-10-17 10:01:12', null, 'f00405vldfSJkGUx44hZ.jpg', '2018-10-17 10:01:12', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/f00405vldfSJkGUx44hZ.jpg', null);
INSERT INTO `tbl_storage` VALUES ('109', '2018-10-17 10:05:18', null, 'GzyzuzcS3f8HyWbIbtze.jpg', '2018-10-17 10:05:18', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/GzyzuzcS3f8HyWbIbtze.jpg', null);
INSERT INTO `tbl_storage` VALUES ('110', '2018-10-17 10:06:22', null, 'G843sakD88MUCQCbTgDx.jpg', '2018-10-17 10:06:22', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/G843sakD88MUCQCbTgDx.jpg', null);
INSERT INTO `tbl_storage` VALUES ('111', '2018-10-17 10:06:49', null, 'rVul6qZYo1IdUdOUDQwp.jpg', '2018-10-17 10:06:49', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/rVul6qZYo1IdUdOUDQwp.jpg', null);
INSERT INTO `tbl_storage` VALUES ('112', '2018-10-17 10:09:01', null, '2IA8GAh18zfxa2cfqgt6.jpg', '2018-10-17 10:09:01', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/2IA8GAh18zfxa2cfqgt6.jpg', null);
INSERT INTO `tbl_storage` VALUES ('113', '2018-10-17 10:09:10', null, 'awH1yvU0fFoWOtgo5XRl.png', '2018-10-17 10:09:10', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/awH1yvU0fFoWOtgo5XRl.png', null);
INSERT INTO `tbl_storage` VALUES ('114', '2018-10-17 10:10:37', null, 'sTrFqysVbfUBUe6tCZwh.jpg', '2018-10-17 10:10:37', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/sTrFqysVbfUBUe6tCZwh.jpg', null);
INSERT INTO `tbl_storage` VALUES ('115', '2018-10-17 10:24:14', null, 'T4qnZAn1Xjmhyt5A5p7S.jpg', '2018-10-17 10:24:14', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/T4qnZAn1Xjmhyt5A5p7S.jpg', null);
INSERT INTO `tbl_storage` VALUES ('116', '2018-10-17 10:28:40', null, 'hMPcCQV7UHCiEmfAMLVT.png', '2018-10-17 10:28:40', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/hMPcCQV7UHCiEmfAMLVT.png', null);
INSERT INTO `tbl_storage` VALUES ('117', '2018-10-17 10:30:15', null, 'XiI9tLcF3NahymGAFuh4.jpg', '2018-10-17 10:30:15', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/XiI9tLcF3NahymGAFuh4.jpg', null);
INSERT INTO `tbl_storage` VALUES ('118', '2018-10-17 10:35:00', null, '7QUx56LkEfB7BDjordSr.png', '2018-10-17 10:35:00', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/7QUx56LkEfB7BDjordSr.png', null);
INSERT INTO `tbl_storage` VALUES ('119', '2018-10-17 10:45:06', null, 'PwqTG9iSKRNT33bLqDwk.jpg', '2018-10-17 10:45:06', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/PwqTG9iSKRNT33bLqDwk.jpg', null);
INSERT INTO `tbl_storage` VALUES ('120', '2018-10-17 11:13:45', null, 'm4m7a8lZtCmMEDdTSWzO.jpg', '2018-10-17 11:13:45', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/m4m7a8lZtCmMEDdTSWzO.jpg', null);
INSERT INTO `tbl_storage` VALUES ('121', '2018-10-17 11:15:04', null, 'YwxDofEHoW9Qx7q2HLxp.jpg', '2018-10-17 11:15:04', '微信图片_20181010100742.jpg', '55231', 'image/jpeg', 'http://localhost:8080/storage/fetch/YwxDofEHoW9Qx7q2HLxp.jpg', null);
INSERT INTO `tbl_storage` VALUES ('122', '2018-10-17 11:15:06', null, 'pvHLEuCgxBaVJEAEMwOa.jpg', '2018-10-17 11:15:06', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/pvHLEuCgxBaVJEAEMwOa.jpg', null);
INSERT INTO `tbl_storage` VALUES ('123', '2018-10-17 11:15:08', null, 'hJVrdI5fo4QBFiBDV2AG.png', '2018-10-17 11:15:08', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/hJVrdI5fo4QBFiBDV2AG.png', null);
INSERT INTO `tbl_storage` VALUES ('124', '2018-10-17 11:22:25', null, 'Z5TZxNh844JGBXP7qLKQ.png', '2018-10-17 11:22:25', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/Z5TZxNh844JGBXP7qLKQ.png', null);
INSERT INTO `tbl_storage` VALUES ('125', '2018-10-17 11:28:46', null, 'K2VX3Mng0w9dAjRfqIdE.jpg', '2018-10-17 11:28:46', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/K2VX3Mng0w9dAjRfqIdE.jpg', null);
INSERT INTO `tbl_storage` VALUES ('126', '2018-10-17 11:28:49', null, 'RFWcqbHf19y9rJjkaY7y.jpg', '2018-10-17 11:28:49', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/RFWcqbHf19y9rJjkaY7y.jpg', null);
INSERT INTO `tbl_storage` VALUES ('127', '2018-10-17 11:30:21', null, 'n1o3bKkHxNAHPMvGBM1E.jpg', '2018-10-17 11:30:21', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/n1o3bKkHxNAHPMvGBM1E.jpg', null);
INSERT INTO `tbl_storage` VALUES ('128', '2018-10-17 11:30:38', null, '3lnm3jvGvp2W3phLOuA9.jpg', '2018-10-17 11:30:38', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/3lnm3jvGvp2W3phLOuA9.jpg', null);
INSERT INTO `tbl_storage` VALUES ('129', '2018-10-17 11:32:35', null, 'IuNQKKSZI8fRehTDqYNv.jpg', '2018-10-17 11:32:35', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/IuNQKKSZI8fRehTDqYNv.jpg', null);
INSERT INTO `tbl_storage` VALUES ('130', '2018-10-17 11:33:28', null, 'V50VYdMxNLhoI0coyLw0.jpg', '2018-10-17 11:33:28', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/V50VYdMxNLhoI0coyLw0.jpg', null);
INSERT INTO `tbl_storage` VALUES ('131', '2018-10-17 11:33:48', null, 'Chmhj3p51sSr2wyyhj38.jpg', '2018-10-17 11:33:48', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/Chmhj3p51sSr2wyyhj38.jpg', null);
INSERT INTO `tbl_storage` VALUES ('132', '2018-10-17 11:33:52', null, '8ta8XYwTeSGrj5hACdmD.jpg', '2018-10-17 11:33:52', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/8ta8XYwTeSGrj5hACdmD.jpg', null);
INSERT INTO `tbl_storage` VALUES ('133', '2018-10-17 11:35:58', null, 'f0OXSgM1MhqXQLZEoVry.jpg', '2018-10-17 11:35:58', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/f0OXSgM1MhqXQLZEoVry.jpg', null);
INSERT INTO `tbl_storage` VALUES ('134', '2018-10-17 11:36:07', null, 'le6GVawZCc4LQkMXAb5m.jpg', '2018-10-17 11:36:07', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/le6GVawZCc4LQkMXAb5m.jpg', null);
INSERT INTO `tbl_storage` VALUES ('135', '2018-10-17 11:36:10', null, '7eLwcKUbpWhK9fftz4os.jpg', '2018-10-17 11:36:10', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/7eLwcKUbpWhK9fftz4os.jpg', null);
INSERT INTO `tbl_storage` VALUES ('136', '2018-10-17 11:36:22', null, 'rAk4CUNTx8osvuIzQILn.jpg', '2018-10-17 11:36:22', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/rAk4CUNTx8osvuIzQILn.jpg', null);
INSERT INTO `tbl_storage` VALUES ('137', '2018-10-17 11:37:25', null, 'ARnkPTMq1qJuAqa6WLHF.jpg', '2018-10-17 11:37:25', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/ARnkPTMq1qJuAqa6WLHF.jpg', null);
INSERT INTO `tbl_storage` VALUES ('138', '2018-10-17 11:37:30', null, 'G9QnTImG1ApXPIbywPvM.jpg', '2018-10-17 11:37:30', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/G9QnTImG1ApXPIbywPvM.jpg', null);
INSERT INTO `tbl_storage` VALUES ('139', '2018-10-17 11:37:33', null, '69NTEH1TU82rgHdTOHRn.png', '2018-10-17 11:37:33', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/69NTEH1TU82rgHdTOHRn.png', null);
INSERT INTO `tbl_storage` VALUES ('140', '2018-10-17 11:42:36', null, 'oXcsy92kJnkvXCs7Y2KT.png', '2018-10-17 11:42:36', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/oXcsy92kJnkvXCs7Y2KT.png', null);
INSERT INTO `tbl_storage` VALUES ('141', '2018-10-17 11:42:38', null, 'HzvyQKG7tYJ5zUaiCMMf.jpg', '2018-10-17 11:42:38', '微信图片_20181010100742.jpg', '55231', 'image/jpeg', 'http://localhost:8080/storage/fetch/HzvyQKG7tYJ5zUaiCMMf.jpg', null);
INSERT INTO `tbl_storage` VALUES ('142', '2018-10-17 11:42:42', null, 'lQwkuG9QryHwtviXkzvz.jpg', '2018-10-17 11:42:42', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/lQwkuG9QryHwtviXkzvz.jpg', null);
INSERT INTO `tbl_storage` VALUES ('143', '2018-10-17 11:42:58', null, 'db3TFjK8Lt268qOmS5GS.jpg', '2018-10-17 11:42:58', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/db3TFjK8Lt268qOmS5GS.jpg', null);
INSERT INTO `tbl_storage` VALUES ('144', '2018-10-17 11:43:00', null, 'OF97sGQMhxn0zcsd8s6d.png', '2018-10-17 11:43:00', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/OF97sGQMhxn0zcsd8s6d.png', null);
INSERT INTO `tbl_storage` VALUES ('145', '2018-10-17 11:43:07', null, '1lYPyakenu4OFe8Ap7v3.jpg', '2018-10-17 11:43:07', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/1lYPyakenu4OFe8Ap7v3.jpg', null);
INSERT INTO `tbl_storage` VALUES ('146', '2018-10-17 11:44:38', null, 'WHUduwuQmYczDhDptTH9.jpg', '2018-10-17 11:44:38', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/WHUduwuQmYczDhDptTH9.jpg', null);
INSERT INTO `tbl_storage` VALUES ('147', '2018-10-17 12:04:36', null, '5cKpgiY1CRfVe6ICfU73.png', '2018-10-17 12:04:36', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/5cKpgiY1CRfVe6ICfU73.png', null);
INSERT INTO `tbl_storage` VALUES ('148', '2018-10-17 12:04:38', null, 'RT2vGBJoyqztbIZSgcge.jpg', '2018-10-17 12:04:38', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/RT2vGBJoyqztbIZSgcge.jpg', null);
INSERT INTO `tbl_storage` VALUES ('149', '2018-10-17 12:04:50', null, 'Gue1vXXU172CHY5Nn3J1.jpg', '2018-10-17 12:04:50', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/Gue1vXXU172CHY5Nn3J1.jpg', null);
INSERT INTO `tbl_storage` VALUES ('150', '2018-10-17 12:10:04', null, 'tLxlnCwyXkin2bGoIjE7.jpg', '2018-10-17 12:10:04', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/tLxlnCwyXkin2bGoIjE7.jpg', null);
INSERT INTO `tbl_storage` VALUES ('151', '2018-10-17 12:10:48', null, 'HUxDniMSnNk2RMoMSIjW.jpg', '2018-10-17 12:10:48', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/HUxDniMSnNk2RMoMSIjW.jpg', null);
INSERT INTO `tbl_storage` VALUES ('152', '2018-10-17 12:10:50', null, '3c3OsiXgdvPqczyaADx7.jpg', '2018-10-17 12:10:50', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/3c3OsiXgdvPqczyaADx7.jpg', null);
INSERT INTO `tbl_storage` VALUES ('153', '2018-10-17 12:11:28', null, 'Gjw2sp36iq8NfV4hyBBN.jpg', '2018-10-17 12:11:28', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/Gjw2sp36iq8NfV4hyBBN.jpg', null);
INSERT INTO `tbl_storage` VALUES ('154', '2018-10-17 12:11:30', null, 'CQGopmuubGbUL3Tt4VjI.jpg', '2018-10-17 12:11:30', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/CQGopmuubGbUL3Tt4VjI.jpg', null);
INSERT INTO `tbl_storage` VALUES ('155', '2018-10-17 12:12:42', null, 'BoyObCu5TJYjB2AllVNc.png', '2018-10-17 12:12:42', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/BoyObCu5TJYjB2AllVNc.png', null);
INSERT INTO `tbl_storage` VALUES ('156', '2018-10-17 12:14:05', null, 'CWv6dSSQIkbjxGuG9lJq.png', '2018-10-17 12:14:05', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/CWv6dSSQIkbjxGuG9lJq.png', null);
INSERT INTO `tbl_storage` VALUES ('157', '2018-10-17 12:14:49', null, 'UUcFzFDKWxCir5ob7BvV.jpg', '2018-10-17 12:14:49', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/UUcFzFDKWxCir5ob7BvV.jpg', null);
INSERT INTO `tbl_storage` VALUES ('158', '2018-10-17 12:15:08', null, 'v3lmS89DF93eRADH27DR.jpg', '2018-10-17 12:15:08', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/v3lmS89DF93eRADH27DR.jpg', null);
INSERT INTO `tbl_storage` VALUES ('159', '2018-10-17 12:15:32', null, 'iFe93LH0DuJUKkDb0Saw.jpg', '2018-10-17 12:15:32', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/iFe93LH0DuJUKkDb0Saw.jpg', null);
INSERT INTO `tbl_storage` VALUES ('160', '2018-10-17 12:16:04', null, 'gh8iWl2DF6vFCzDiqbTn.jpg', '2018-10-17 12:16:04', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/gh8iWl2DF6vFCzDiqbTn.jpg', null);
INSERT INTO `tbl_storage` VALUES ('161', '2018-10-17 12:16:16', null, '83nIvWjYo4RKH1BHQb4L.jpg', '2018-10-17 12:16:16', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/83nIvWjYo4RKH1BHQb4L.jpg', null);
INSERT INTO `tbl_storage` VALUES ('162', '2018-10-17 12:18:14', null, '9lj7AEnBV57V05Mp9KDH.jpg', '2018-10-17 12:18:14', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/9lj7AEnBV57V05Mp9KDH.jpg', null);
INSERT INTO `tbl_storage` VALUES ('163', '2018-10-17 12:18:42', null, 'p1IhuDL3nKlJ8vewuECE.jpg', '2018-10-17 12:18:42', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/p1IhuDL3nKlJ8vewuECE.jpg', null);
INSERT INTO `tbl_storage` VALUES ('164', '2018-10-17 12:18:45', null, 'PQI2JXJ87Qp03PncemH9.png', '2018-10-17 12:18:45', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/PQI2JXJ87Qp03PncemH9.png', null);
INSERT INTO `tbl_storage` VALUES ('165', '2018-10-17 12:19:17', null, '3wFJFuFv5qpMfGZZsxzo.jpg', '2018-10-17 12:19:17', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/3wFJFuFv5qpMfGZZsxzo.jpg', null);
INSERT INTO `tbl_storage` VALUES ('166', '2018-10-17 12:20:11', null, '5TyJ6Fc7UlUrBCIuO5dm.jpg', '2018-10-17 12:20:11', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/5TyJ6Fc7UlUrBCIuO5dm.jpg', null);
INSERT INTO `tbl_storage` VALUES ('167', '2018-10-17 12:20:39', null, 'ee1Ot8Vj2MnJnWBtdwK3.jpg', '2018-10-17 12:20:39', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/ee1Ot8Vj2MnJnWBtdwK3.jpg', null);
INSERT INTO `tbl_storage` VALUES ('168', '2018-10-17 12:23:08', null, 'oNVrBxnqny0WUtDMPMFW.jpg', '2018-10-17 12:23:08', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/oNVrBxnqny0WUtDMPMFW.jpg', null);
INSERT INTO `tbl_storage` VALUES ('169', '2018-10-17 12:23:10', null, 'XieH9pCrX5HWDbiQetni.png', '2018-10-17 12:23:10', '微信截图_20180914122613.png', '16698', 'image/png', 'http://localhost:8080/storage/fetch/XieH9pCrX5HWDbiQetni.png', null);
INSERT INTO `tbl_storage` VALUES ('170', '2018-10-17 12:23:12', null, 'BcNIVyMBYAam9tBVQVtm.jpg', '2018-10-17 12:23:12', 'qrcode_for_gh_d1521321e987_258.jpg', '27589', 'image/jpeg', 'http://localhost:8080/storage/fetch/BcNIVyMBYAam9tBVQVtm.jpg', null);
INSERT INTO `tbl_storage` VALUES ('171', '2018-10-17 12:23:14', null, 'VbRqBNJwpKEGm4jPFQ8F.jpg', '2018-10-17 12:23:14', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/VbRqBNJwpKEGm4jPFQ8F.jpg', null);
INSERT INTO `tbl_storage` VALUES ('172', '2018-10-17 12:30:27', null, '4XzzJddAOMuj8PVqMI7s.jpg', '2018-10-17 12:30:27', 'timg.jpg', '75743', 'image/jpeg', 'http://localhost:8080/storage/fetch/4XzzJddAOMuj8PVqMI7s.jpg', null);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `integral` int(11) DEFAULT '0',
  `invitation_code` varchar(255) DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `last_login_time` bigint(20) DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL,
  `money_all_used` double DEFAULT '0',
  `money_can_used` double DEFAULT '0',
  `money_layered` double DEFAULT '0',
  `money_normal` double DEFAULT '0',
  `nickname` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo_img` varchar(255) DEFAULT NULL,
  `promotion_id` int(11) DEFAULT NULL,
  `recommend_time` bigint(20) DEFAULT NULL,
  `register_time` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `wx_openid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('1', '2018-10-15', '1', '0', '424339', '192.168.10.108', '1539239731277', '0', '0', '0', '0', '0', '球球', null, '1', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIMicTDpvvtCjEgL1nsraREC5DcFHH9Hutm6sX8OLAuLBE7lPvBwmOYCkZQW811Q6cGRic7OcssWZPQ/132', '0', null, '1539239731277', '1', '1', '1', 'olBoZ44EjTMiinlUgi_CKDFoCU-k');

-- ----------------------------
-- Table structure for tbl_user_apply
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_apply`;
CREATE TABLE `tbl_user_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agreed_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_apply
-- ----------------------------
INSERT INTO `tbl_user_apply` VALUES ('1', '1539239773457', '1539239731272', '1', '', 'olBoZ44EjTMiinlUgi_CKDFoCU-k', '1', '1', '1');

-- ----------------------------
-- Table structure for tbl_user_keyword
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_keyword`;
CREATE TABLE `tbl_user_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_keyword
-- ----------------------------
INSERT INTO `tbl_user_keyword` VALUES ('1', '11', '1', '1', '1539251491410');
INSERT INTO `tbl_user_keyword` VALUES ('2', '2', '关键字1', '1', '1539251524005');
INSERT INTO `tbl_user_keyword` VALUES ('3', '1', '5', '1', '1539251873919');
INSERT INTO `tbl_user_keyword` VALUES ('4', '2', '无', '1', '1539251894549');
INSERT INTO `tbl_user_keyword` VALUES ('5', '1', '无法', '1', '1539251910365');
INSERT INTO `tbl_user_keyword` VALUES ('6', '3', '13', '1', '1539252813428');
INSERT INTO `tbl_user_keyword` VALUES ('7', '1', '12', '1', '1539252816155');
INSERT INTO `tbl_user_keyword` VALUES ('8', '1', '14', '1', '1539252817877');
INSERT INTO `tbl_user_keyword` VALUES ('9', '1', '46', '1', '1539252973695');
INSERT INTO `tbl_user_keyword` VALUES ('10', '1', '23', '1', '1539253334394');
INSERT INTO `tbl_user_keyword` VALUES ('11', '1', '123', '1', '1539253515284');
INSERT INTO `tbl_user_keyword` VALUES ('12', '2', '车', '1', '1539255594077');
INSERT INTO `tbl_user_keyword` VALUES ('13', '1', '的', '1', '1539255777049');
INSERT INTO `tbl_user_keyword` VALUES ('14', '1', '滴的', '1', '1539255795222');
INSERT INTO `tbl_user_keyword` VALUES ('15', '1', '就', '1', '1539255904033');

-- ----------------------------
-- Table structure for tbl_user_region
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_region`;
CREATE TABLE `tbl_user_region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `note_explain` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user_region
-- ----------------------------
