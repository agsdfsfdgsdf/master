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

 Date: 09/06/2023 14:16:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vel_aligning_data
-- ----------------------------
DROP TABLE IF EXISTS `vel_aligning_data`;
CREATE TABLE `vel_aligning_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `container_dev` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对位设备类型,激光对位，视觉对位，龙门吊对位 ',
  `Timestamp` timestamp NULL DEFAULT NULL COMMENT '时间戳,yyyy-MM-dd HH:mm:ss ',
  `DeviceNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备号',
  `TruckNo` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集卡号, ',
  `container_no` int(11) NULL DEFAULT NULL COMMENT '具体移动的物理值, ',
  `Rate` float NULL DEFAULT NULL COMMENT '具体移动的百分比,匹配Toss任务ID ',
  `Control_Mode` bit(1) NULL DEFAULT NULL COMMENT '操作模式,True自动对位 False 人工对位 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vel_control_data
-- ----------------------------
DROP TABLE IF EXISTS `vel_control_data`;
CREATE TABLE `vel_control_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `device_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车号, TOS中车号',
  `longitude_r` double NOT NULL COMMENT '实际经度',
  `latitude_r` double NOT NULL COMMENT '实际纬度',
  `longitude_d` double NOT NULL COMMENT '规划经度',
  `latitude_d` double NOT NULL COMMENT '规划纬度',
  `speed_r` float NOT NULL COMMENT '实际速度    单位km/h',
  `speed_d` int(11) NOT NULL COMMENT '规划速度    单位km/h',
  `speed_l` int(11) NOT NULL COMMENT '区域限速    单位km/h',
  `throttle_r` float NOT NULL COMMENT '油门控制    取值范围0.0～1.0，代表期望油门的百分比。0.0对应0%不踩油门，1.0对应100%满油。油门期望/控制与反馈量和决策目标值差量之间统一度量衡',
  `throttle_d` float NOT NULL COMMENT '油门期望',
  `throttle_s` float NOT NULL COMMENT '底层油门反馈量与决策目标量差值',
  `brake_r` float NOT NULL COMMENT '刹车控制    取值范围0.0～1.0，代表期望刹车的百分比。0.0对应0%不踩刹车，1.0对应100%踩死',
  `brake_d` float NOT NULL COMMENT '刹车期望',
  `brake_s` float NOT NULL COMMENT '底层制动反馈量与决策目标量差值',
  `wheel_r` float NOT NULL COMMENT '转向控制    取值范围-1.0~1.0，代表反馈方向盘转角的百分比。-1.0对应向左打满100%，0.0代表无转向，1.0代表向右打满100%',
  `wheel_d` float NOT NULL COMMENT '转向期望',
  `wheel_s` float NOT NULL COMMENT '底层转向反馈信息与决策目标量差值',
  `gear_r` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '挡位控制    取值范围：D、R、P、N，代表前进挡、倒车档、驻车档、空挡',
  `gear_d` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '挡位期望',
  `light_r` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车灯控制',
  `light_d` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车灯期望  取值范围：L、R、E、N，代表左转向、右转向、双闪、无',
  `is_cp` int(11) NOT NULL COMMENT '是否对位  对位中1，非对位0',
  `cp_start` int(11) NOT NULL COMMENT '是否对位开始  距目标点5米时触发；是：1，否：0',
  `cp_end` int(11) NOT NULL COMMENT '是否对位结束  无人集卡确认对位完成；是：1，否：0',
  `device_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间  yyyy-MM-dd HH:mm:ss',
  `position` int(11) NOT NULL COMMENT '所在位置   1、集中装卸锁站 2、0W查验场地 3、熏蒸场地 4、CFS过磅场地 5、H986查验场地 6、调箱门场地 7、其它指定点位',
  `dev_distance` int(11) NOT NULL COMMENT '车道中心线偏移',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vel_mission_data
-- ----------------------------
DROP TABLE IF EXISTS `vel_mission_data`;
CREATE TABLE `vel_mission_data`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `container_dev` int(11) NULL DEFAULT NULL COMMENT '落箱距离误差值  集卡背箱时与标准位置的误差判断',
  `receivung_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '接受指令时间  yyyy-MM-dd HH:mm:ss',
  `finish_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '指令完成时间  yyyy-MM-dd HH:mm:ss',
  `device_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集卡号',
  `container_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业箱号',
  `id` int(20) NOT NULL COMMENT '匹配Toss任务ID',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vel_status_data
-- ----------------------------
DROP TABLE IF EXISTS `vel_status_data`;
CREATE TABLE `vel_status_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车号，tos中车号',
  `fleet` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属车队',
  `energy` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '能源模式，用油：O；用电：E；油电混合：M',
  `login_status` int(11) NOT NULL COMMENT '登录状态，登录：1；未登录：0；故障：9',
  `operation_mode` int(11) NOT NULL COMMENT '作业模式，自动驾驶：0；人工驾驶：1；未登录：默认为-1',
  `version` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号，当前发布的版本号',
  `device_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间，yyyy-MM-dd HH:mm:ss',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 689 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for vel_warn_data
-- ----------------------------
DROP TABLE IF EXISTS `vel_warn_data`;
CREATE TABLE `vel_warn_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `device_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集卡号,TOS中车号 ',
  `device_time` timestamp NULL DEFAULT NULL COMMENT '时间,yyyy-MM-dd HH:mm:ss ',
  `warning_turnlight` bit(1) NULL DEFAULT NULL COMMENT '灯光（左转，右转，双闪）',
  `warning_speaker` bit(1) NULL DEFAULT NULL COMMENT '喇叭',
  `warning_tirepress` bit(1) NULL DEFAULT NULL COMMENT '胎压',
  `warning_seatbelt` bit(1) NULL DEFAULT NULL COMMENT '安全带',
  `warning_backlight` bit(1) NULL DEFAULT NULL COMMENT '拖挂灯控',
  `warning_press` bit(1) NULL DEFAULT NULL COMMENT '拖挂气路',
  `warning_wiper` bit(1) NULL DEFAULT NULL COMMENT '雨刷',
  `warning_frontlight` bit(1) NULL DEFAULT NULL COMMENT '大灯',
  `warning_led` bit(1) NULL DEFAULT NULL COMMENT 'LED屏',
  `warning_alarm` bit(1) NULL DEFAULT NULL COMMENT '警示灯',
  `warning_break` bit(1) NULL DEFAULT NULL COMMENT '无底层制动反馈信息',
  `warning_acc` bit(1) NULL DEFAULT NULL COMMENT '无底层油门反馈信息',
  `warning_turn` bit(1) NULL DEFAULT NULL COMMENT '无底层转角反馈信息',
  `laser_lag` bit(1) NULL DEFAULT NULL COMMENT '激光雷达有无数据',
  `singlelaser` bit(1) NULL DEFAULT NULL COMMENT '激光雷达单帧点数异常',
  `multilaser` bit(1) NULL DEFAULT NULL COMMENT '激光雷达噪点异常',
  `cameradata` bit(1) NULL DEFAULT NULL COMMENT '摄像头无数据',
  `camera_lag` bit(1) NULL DEFAULT NULL COMMENT '摄像头数据异常-画面静止',
  `mmradar_lag` bit(1) NULL DEFAULT NULL COMMENT '毫米波雷达无数据',
  `mmrader_bug` bit(1) NULL DEFAULT NULL COMMENT '毫米波雷达数据异常-障碍物信息异常',
  `wheelspeed_lag` bit(1) NULL DEFAULT NULL COMMENT '轮速计无数据',
  `wheelspeed_SD` bit(1) NULL DEFAULT NULL COMMENT '轮速计标准差过大',
  `anglearsensor_lag` bit(1) NULL DEFAULT NULL COMMENT '转角传感器无数据',
  `warning_hardbrake` bit(1) NULL DEFAULT NULL COMMENT '急刹',
  `warning_LWT` bit(1) NULL DEFAULT NULL COMMENT '长时间等待',
  `turn_mainstreet` bit(1) NULL DEFAULT NULL COMMENT '转角阈值',
  `turn_inbay` bit(1) NULL DEFAULT NULL COMMENT '转角阈值',
  `turn_dock` bit(1) NULL DEFAULT NULL COMMENT '转角阈值',
  `turn_charge` bit(1) NULL DEFAULT NULL COMMENT '转角阈值',
  `turn_curve` bit(1) NULL DEFAULT NULL COMMENT '转角阈值',
  `GNSS_data` bit(1) NULL DEFAULT NULL COMMENT 'GNSS定位无数据',
  `GNSS_lag` bit(1) NULL DEFAULT NULL COMMENT 'GNSS定位信号弱',
  `IMU_data` bit(1) NULL DEFAULT NULL COMMENT 'IMU无数据',
  `IMU_sd` bit(1) NULL DEFAULT NULL COMMENT 'IMU帧间方差过大',
  `location_sd` bit(1) NULL DEFAULT NULL COMMENT '融合定位误差过大',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
