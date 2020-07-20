/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : jeccgboot

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/07/2020 14:30:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for filterelement
-- ----------------------------
DROP TABLE IF EXISTS `filterelement`;
CREATE TABLE `filterelement`  (
  `filterelement_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '滤芯编号',
  `filterelement_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '滤芯名称',
  `validity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '可用天数',
  `replacementdays` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最低更换天数',
  `Images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '濾芯图片',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`filterelement_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '滤芯表\r\n' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of filterelement
-- ----------------------------
INSERT INTO `filterelement` VALUES ('2132ewqewqrfew', '我老鸡巴强了', '180', '10', 'files/20191030/8b1317e1d1ef4c93a8e558fb6891025f_1571729475026_1572401884159.jpg', '王浩', '2019-10-19 16:19:53', 'admin', '2019-11-07 14:11:48');
INSERT INTO `filterelement` VALUES ('9ec0a30835e45045d41d59f6f7d09f23', '321', '180', '10', 'files/20191030/2121_1569574536297_1571728030384_1572401503919.png', 'admin', '2019-10-30 10:05:05', 'admin', '2019-11-07 14:11:53');
INSERT INTO `filterelement` VALUES ('f4ee41bc0be435bfe546b13051d36983', '测试一号', '1', NULL, 'files/20191111/QQ截图20190928104220_1573454424391.png', 'jeecg', '2019-11-11 14:45:39', NULL, '2019-11-11 14:45:39');

SET FOREIGN_KEY_CHECKS = 1;
