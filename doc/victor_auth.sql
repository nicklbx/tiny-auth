/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : victor_auth

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2022-08-28 13:42:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pms_product
-- ----------------------------
DROP TABLE IF EXISTS `pms_product`;
CREATE TABLE `pms_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `description` varchar(100) DEFAULT NULL COMMENT '商品描述',
  `pic` varchar(100) DEFAULT NULL COMMENT '图片',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `status` int(5) DEFAULT '0' COMMENT '状态 0-启用 1-禁用',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` int(5) DEFAULT NULL COMMENT '0-启用 1-已删除',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of pms_product
-- ----------------------------
INSERT INTO `pms_product` VALUES ('108', '小米路由器', '小米路由器', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg', '200.00', '1', '2022-08-27 17:44:30', '2022-08-27 18:15:27', '1');
INSERT INTO `pms_product` VALUES ('109', '小米路由器', '小米路由器', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180615/5b02804dN66004d73.jpg', '200.00', '0', '2022-08-27 18:15:32', '2022-08-27 18:15:32', '0');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称 唯一标识一个资源元素',
  `level` int(5) DEFAULT NULL COMMENT '层级0 1 2',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `type_id` int(5) DEFAULT NULL COMMENT '资源类型 0-菜单 1-按钮',
  `type_name` varchar(100) DEFAULT NULL COMMENT '资源类型名称',
  `url` varchar(100) DEFAULT NULL COMMENT '接口地址 请求方法+path',
  `description` varchar(100) DEFAULT NULL COMMENT '资源描述',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `unique_resource_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', null, '0', '0', '0', '菜单', null, '权限管理', null, '0', null, null);
INSERT INTO `sys_resource` VALUES ('2', null, '0', '0', '0', '菜单', null, '商品管理', null, '0', null, null);
INSERT INTO `sys_resource` VALUES ('4', null, '1', '1', '0', '菜单', null, '用户管理', null, '0', null, null);
INSERT INTO `sys_resource` VALUES ('5', null, '1', '1', '0', '菜单', null, '角色管理', null, '0', null, null);
INSERT INTO `sys_resource` VALUES ('6', null, '1', '1', '0', '菜单', null, '资源管理', null, '0', null, null);
INSERT INTO `sys_resource` VALUES ('7', null, '1', '1', '0', '菜单', null, '商品列表', null, '0', null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `sort` int(5) DEFAULT NULL COMMENT '排序',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '超级管理员', '0', '2022-08-27 19:41:58', '2022-08-27 19:41:58');
INSERT INTO `sys_role` VALUES ('2', 'user', '普通用户', '0', '2022-08-27 19:42:28', '2022-08-27 19:42:28');

-- ----------------------------
-- Table structure for sys_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource_relation`;
CREATE TABLE `sys_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_role_resource` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_resource_relation
-- ----------------------------
INSERT INTO `sys_role_resource_relation` VALUES ('1', '1', '1', '2022-08-27 22:08:06', '2022-08-27 22:08:06');
INSERT INTO `sys_role_resource_relation` VALUES ('2', '1', '2', '2022-08-27 22:08:06', '2022-08-27 22:08:06');
INSERT INTO `sys_role_resource_relation` VALUES ('3', '1', '3', '2022-08-27 22:08:06', '2022-08-27 22:08:06');
INSERT INTO `sys_role_resource_relation` VALUES ('4', '1', '4', '2022-08-27 22:08:06', '2022-08-27 22:08:06');
INSERT INTO `sys_role_resource_relation` VALUES ('5', '1', '5', '2022-08-27 22:08:06', '2022-08-27 22:08:06');
INSERT INTO `sys_role_resource_relation` VALUES ('6', '1', '6', '2022-08-27 22:08:06', '2022-08-27 22:08:06');
INSERT INTO `sys_role_resource_relation` VALUES ('7', '1', '7', '2022-08-27 22:08:06', '2022-08-27 22:08:06');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '图片',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `status` int(5) DEFAULT '0' COMMENT '状态 0启用 1禁用',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `unique_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', '超级管理员', '13800138000', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', '0', '2022-08-27 19:36:12', '2022-08-27 19:36:56');

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
INSERT INTO `sys_user_role_relation` VALUES ('3', '1', '1', '2022-08-27 21:28:59', '2022-08-27 21:28:59');
INSERT INTO `sys_user_role_relation` VALUES ('4', '1', '2', '2022-08-27 21:28:59', '2022-08-27 21:28:59');
