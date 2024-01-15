/*
Navicat MySQL Data Transfer

Source Server         : qq
Source Server Version : 80021
Source Host           : localhost:3306
Source Database       : oldqq

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-12-09 15:34:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` int NOT NULL AUTO_INCREMENT,
  `msg_content` varchar(2000) DEFAULT NULL,
  `msg_sendfrom` int DEFAULT NULL,
  `msg_sendto` int DEFAULT NULL,
  `msg_sendtime` datetime DEFAULT NULL,
  `msg_remark` varchar(3000) DEFAULT NULL,
  `msg_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
-- ----------------------------
INSERT INTO `msg` VALUES ('24', 'qqq', '1', '7', '2021-12-06 14:37:20', '', '');
INSERT INTO `msg` VALUES ('37', '111', '4', '5', '2021-12-06 21:40:44', '', '');
INSERT INTO `msg` VALUES ('54', '蜡笔小新，要来我家玩吗', '1', '5', '2021-12-09 09:18:54', '', '');
INSERT INTO `msg` VALUES ('56', '阿姨好', '1', '7', '2021-12-09 09:19:20', '', '');

-- ----------------------------
-- Table structure for tb_note
-- ----------------------------
DROP TABLE IF EXISTS `tb_note`;
CREATE TABLE `tb_note` (
  `noteID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `u_id` int DEFAULT NULL,
  PRIMARY KEY (`noteID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_note
-- ----------------------------
INSERT INTO `tb_note` VALUES ('1', '生日', '我今年五岁啦', '1');
INSERT INTO `tb_note` VALUES ('2', '我的爸爸', '我的爸爸叫胡英俊，他有一张俊俏的脸庞，他最爱陪我玩积木', '1');
INSERT INTO `tb_note` VALUES ('3', '我的妈妈', '我的妈妈叫美伢，她是一个凶巴巴的女人，她老是说我是全世界最顽皮的小孩子，她喜欢揪我脸蛋', '5');
INSERT INTO `tb_note` VALUES ('4', '我的电脑', '我有一台新电脑', '1');
INSERT INTO `tb_note` VALUES ('5', '我的妹妹', '叫小葵，她很漂亮', '5');
INSERT INTO `tb_note` VALUES ('6', '1111', '', '1');
INSERT INTO `tb_note` VALUES ('7', '我的妈妈', '我的妈妈特别可爱', '1');
INSERT INTO `tb_note` VALUES ('8', '我的玩家', '2222', '1');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_name` varchar(50) DEFAULT NULL,
  `u_pwd` varchar(50) DEFAULT NULL,
  `u_ip` varchar(50) DEFAULT NULL,
  `u_state` varchar(50) DEFAULT NULL,
  `u_gender` varchar(50) DEFAULT NULL,
  `u_email` varchar(50) DEFAULT NULL,
  `u_last_login` datetime DEFAULT NULL,
  `u_last_exit` datetime DEFAULT NULL,
  `u_remarke` varchar(3000) DEFAULT NULL,
  `u_signature` varchar(100) DEFAULT NULL,
  `u_head_img` varchar(100) DEFAULT NULL,
  `u_type` varchar(50) DEFAULT NULL,
  `u_birthday` date DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '胡图图', '123', '127.0.0.1', '-1', '男', 'Htutu@qq.com', '2021-12-09 14:06:33', '2021-12-09 14:10:37', null, '我叫胡图图，我的爸爸叫胡英俊，我的妈妈叫张小丽', '0', null, '2000-06-01');
INSERT INTO `users` VALUES ('2', '胡英俊', '123', '127.0.0.1', '-1', '男', 'Hyj@qq.com', '2021-12-09 14:07:09', '2021-12-09 14:09:48', null, '我家住在翻斗花园二号楼一零零一室', '3', null, '1977-07-28');
INSERT INTO `users` VALUES ('4', '张小丽', '123', '127.0.0.1', '-1', '女', '', '2021-12-09 13:43:19', '2021-12-09 13:43:56', null, '我就是天才的图图老妈', '1', null, null);
INSERT INTO `users` VALUES ('5', '蜡笔小新', '123', '127.0.0.1', '-1', '男', null, '2021-12-06 20:15:01', '2021-12-06 20:15:46', null, null, '2', null, null);
INSERT INTO `users` VALUES ('6', '小白', '123', '127.0.0.1', '-1', '男', '', '2021-12-09 13:41:26', '2021-12-09 13:43:59', null, '我是小白~', '7', null, '1992-07-28');
INSERT INTO `users` VALUES ('7', '美伢', '123', '127.0.0.1', '-1', '女', '', '2021-12-06 14:36:45', '2021-12-06 14:35:45', null, '小新！', '4', null, '2000-11-11');
INSERT INTO `users` VALUES ('8', '喜羊羊', '123', '127.0.0.1', '-1', '男', '', '2021-12-09 14:09:17', '2021-12-09 14:09:46', null, '我是喜羊羊', '8', null, '2001-07-28');
INSERT INTO `users` VALUES ('11', '懒羊羊', '123', '127.0.0.1', '0', '男', '', '2021-12-09 14:11:43', null, null, '', '9', null, '2001-07-28');

-- ----------------------------
-- View structure for view_usergroup
-- ----------------------------
DROP VIEW IF EXISTS `view_usergroup`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_usergroup` AS select `dw_group`.`group_id` AS `group_id`,`dw_group`.`group_name` AS `group_name`,`dw_group`.`group_avatar` AS `group_avatar`,`dw_group`.`group_trades` AS `group_trades`,`dw_usergroup`.`user_id` AS `user_id` from (`dw_group` join `dw_usergroup`) where (`dw_group`.`group_id` = `dw_usergroup`.`group_id`) ;

-- ----------------------------
-- View structure for view_useruser
-- ----------------------------
DROP VIEW IF EXISTS `view_useruser`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_useruser` AS select `dw_useruser`.`myself` AS `myself`,`dw_useruser`.`myfriend` AS `myfriend`,`dw_user`.`user_name` AS `user_name`,`dw_user`.`user_avatar` AS `user_avatar`,`dw_user`.`user_trades` AS `user_trades` from (`dw_user` join `dw_useruser`) where (`dw_user`.`user_id` = `dw_useruser`.`myfriend`) order by `dw_useruser`.`myself` ;
