/*
 Navicat Premium Data Transfer

 Source Server         : daxie
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : 47.101.174.88:3306
 Source Schema         : daxie

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 09/06/2023 14:15:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vehcile_statistic
-- ----------------------------
DROP TABLE IF EXISTS `vehcile_statistic`;
CREATE TABLE `vehcile_statistic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duration` float NULL DEFAULT NULL,
  `mileage` double NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_bcm
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_bcm`;
CREATE TABLE `vehicle_bcm`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增序号',
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆id',
  `vehicleType` smallint(6) NULL DEFAULT NULL COMMENT '车辆标准类型',
  `lgtEnable` smallint(6) NULL DEFAULT NULL COMMENT '灯光控制使能状态',
  `rightLgt` smallint(6) NULL DEFAULT NULL COMMENT '右转灯状态反馈',
  `leftLgt` smallint(6) NULL DEFAULT NULL COMMENT '左转灯状态反馈',
  `brkLgt` smallint(6) NULL DEFAULT NULL COMMENT '制动灯状态反馈',
  `revLgt` smallint(6) NULL DEFAULT NULL COMMENT '倒车灯状态反馈',
  `sideLgt` smallint(6) NULL DEFAULT NULL COMMENT '示廓灯状态反馈',
  `lowBeamLgt` smallint(6) NULL DEFAULT NULL COMMENT '近光灯状态反馈',
  `highBeamLgt` smallint(6) NULL DEFAULT NULL COMMENT '远光灯状态反馈',
  `frontFogLgt` smallint(6) NULL DEFAULT NULL COMMENT '前雾灯状态反馈',
  `rearFogLgt` smallint(6) NULL DEFAULT NULL COMMENT '后雾灯状态反馈',
  `horn` smallint(6) NULL DEFAULT NULL COMMENT '喇叭状态反馈',
  `rainWiperGear` smallint(6) NULL DEFAULT NULL COMMENT '雨刮状态反馈',
  `wiperWash` smallint(6) NULL DEFAULT NULL COMMENT '喷淋档状态信号',
  `lDrDoor` smallint(6) NULL DEFAULT NULL COMMENT '主驾门开关状态反馈',
  `rDrDoor` smallint(6) NULL DEFAULT NULL COMMENT '副驾门开关状态反馈',
  `coolantLowLevel` smallint(6) NULL DEFAULT NULL COMMENT '冷却液液位低',
  `fuelMass` double NULL DEFAULT NULL COMMENT '燃油油量信号',
  `soc` double NULL DEFAULT NULL COMMENT '电量',
  `voltage` double NULL DEFAULT NULL COMMENT '电压',
  `tyreID` smallint(6) NULL DEFAULT NULL COMMENT '轮胎位置',
  `tyreState` smallint(6) NULL DEFAULT NULL COMMENT '轮胎状态',
  `tyrePressure` double NULL DEFAULT NULL COMMENT '胎压',
  `tyreTemp` double NULL DEFAULT NULL COMMENT '胎温',
  `leakRate` double NULL DEFAULT NULL COMMENT '漏气速率',
  `failureLevel` smallint(6) NULL DEFAULT NULL COMMENT '故障等级',
  `workActive` smallint(6) NULL DEFAULT NULL COMMENT '车辆功能激活使能反馈',
  `driveFailureCode_Jh` bigint(20) NULL DEFAULT NULL COMMENT '驱动故障代码',
  `steerFailureCode_Jh` bigint(20) NULL DEFAULT NULL COMMENT '转向故障代码',
  `brakeFailureCode_Jh` bigint(20) NULL DEFAULT NULL COMMENT '制动故障代码',
  `time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27650 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_camera
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_camera`;
CREATE TABLE `vehicle_camera`  (
  `cameraId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '摄像机id',
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '辆车id',
  `cameraPosition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '像机摄位置',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '码密',
  `accessToken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权码授',
  `accessUpdateId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新更地址',
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cameraId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_camera_access
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_camera_access`;
CREATE TABLE `vehicle_camera_access`  (
  `accessUpdateId` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权码更新地址',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新方法',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求头参数',
  `appKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钥匙',
  `appSecret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秘钥',
  PRIMARY KEY (`accessUpdateId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_info
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_info`;
CREATE TABLE `vehicle_info`  (
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `plateNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `protocol` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vehicleType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vehicleStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `tsc_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'tsc密码，加密存储',
  `device_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集卡号',
  PRIMARY KEY (`vin`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_location
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_location`;
CREATE TABLE `vehicle_location`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vin` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '辆车vin',
  `longitude` double NOT NULL COMMENT '度经',
  `latitude` double NULL DEFAULT NULL COMMENT '度纬',
  `speed` float NULL DEFAULT NULL COMMENT '度速',
  `heading` float NULL DEFAULT NULL COMMENT '向角航',
  `height` float NULL DEFAULT NULL COMMENT '拔海',
  `time` bigint(20) NOT NULL COMMENT '数据采集时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2188448503 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_obstacle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_obstacle`;
CREATE TABLE `vehicle_obstacle`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `longitude` float NULL DEFAULT NULL,
  `latitude` float NULL DEFAULT NULL,
  `altitude` float NULL DEFAULT NULL COMMENT '坐标高程',
  `length` float NULL DEFAULT NULL,
  `width` float NULL DEFAULT NULL,
  `height` float NULL DEFAULT NULL,
  `speed` float NULL DEFAULT NULL,
  `heading` float NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16425 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_apply
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_apply`;
CREATE TABLE `vehicle_ota_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_install_progress
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_install_progress`;
CREATE TABLE `vehicle_ota_install_progress`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataType` int(11) NULL DEFAULT NULL,
  `oldVersionCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VersionCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `progress` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_oss
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_oss`;
CREATE TABLE `vehicle_ota_oss`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `endpoint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bucketName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accessKeyId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `secretAccessKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_publish
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_publish`;
CREATE TABLE `vehicle_ota_publish`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `versionCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataType` int(11) NULL DEFAULT NULL,
  `status` smallint(6) NULL DEFAULT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_update_list
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_update_list`;
CREATE TABLE `vehicle_ota_update_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oldVersionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oldVersionCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VersionName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VersionCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_version
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_version`;
CREATE TABLE `vehicle_ota_version`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增',
  `versionCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dataType` int(11) NULL DEFAULT NULL,
  `fileName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vehicleType` int(11) NULL DEFAULT NULL,
  `protocol` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uploader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `updateType` int(11) NULL DEFAULT NULL,
  `fileSize` bigint(20) NULL DEFAULT NULL,
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sign` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `fileUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vehicle_ota_vin_version
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_ota_vin_version`;
CREATE TABLE `vehicle_ota_vin_version`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `versionId` int(11) NULL DEFAULT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
