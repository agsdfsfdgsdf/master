package com.deyi.daxie.cloud.operation.utils;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum TypEnum {
    warningTurnlight("warningTurnlight", "灯光","一级报警"),
    warningSpeaker("warningSpeaker", "喇叭","一级报警"),
    warningTirepress("warningTirepress", "胎压","一级报警"),
    warningSeatbelt("warningSeatbelt", "安全带","一级报警"),
    warningBacklight("warningBacklight", "拖挂灯控","一级报警"),
    warningPress("warningPress", "拖挂气路","一级报警"),
    warningWiper("warningWiper", "雨刷","一级报警"),
    warningFrontlight("warningFrontlight", "大灯","二级报警"),
    warningLed("warningLed", "LED屏","一级报警"),
    warningAlarm("warningAlarm", "警示灯","一级报警"),
    warningBreak("warningBreak", "无底层制动反馈信息","一级报警"),
    warningAcc("warningAcc", "无底层油门反馈信息","一级报警"),
    warningTurn("warningTurn", "无底层转角反馈信息","一级报警"),
    laserLag("laserLag", "激光雷达有无数据","一级报警"),
    singlelaser("singlelaser", "激光雷达单帧点数异常","一级报警"),
    multilaser("multilaser", "激光雷达噪点异常","一级报警"),
    cameradata("cameradata", "摄像头无数据","一级报警"),
    cameraLag("cameraLag", "摄像头数据异常-画面静止","一级报警"),
    mmradarLag("mmradarLag", "毫米波雷达无数据","一级报警"),
    mmraderBug("mmraderBug", "毫米波雷达数据异常-障碍物信息异常","一级报警"),
    wheelspeedLag("wheelspeedLag", "轮速计无数据","一级报警"),
    wheelspeedSd("wheelspeedSd", "轮速计标准差过大","一级报警"),
    anglearsensorLag("anglearsensorLag", "转角传感器无数据","一级报警"),
    warningHardbrake("warningHardbrake", "急刹","一级报警"),
    warningLwt("warningLwt", "长时间等待","二级报警"),
    turnMainstreet("turnMainstreet", "转角阈值","一级报警"),
    turnInbay("turnInbay", "转角阈值","一级报警"),
    turnDock("turnDock", "转角阈值","一级报警"),
    turnCharge("turnCharge", "转角阈值","二级报警"),
    turnCurve("turnCurve", "转角阈值","一级报警"),
    gnssData("gnssData", "GNSS定位无数据","一级报警"),
    gnssLag("gnssLag", "GNSS定位信号弱","二级报警"),
    imuData("imuData", "IMU无数据","一级报警"),
    imuSd("imuSd", "IMU帧间方差过大","二级报警"),
    locationSd("locationSd", "融合定位误差过大","一级报警");

        /**
         * 接口类型
         */
        private final String type;
        /**
         * 接口描述
         */
        private final String alias;


        private final String desc;

        /**
         * 构造函数
         *
         * @param type  type
         * @param alias alias
         */
         TypEnum(String type, String alias, String desc) {
            this.type = type;
            this.alias = alias;
            this.desc = desc;
        }

        /**
         * 获取值
         *
         * @return String
         */
        public String getType() {
            return this.type;
        }

        /**
         * 获取接口描述
         *
         * @return String
         */
        public String getAlias() {
            return this.alias;
        }
    /**
     * 获取接口描述
     *
     * @return String
     */
    public String getDesc() {
        return this.desc;
    }


       /**
          * 根据value返回枚举类型,主要在switch中使用
         *
         * @param value value
         * @return DataType
         */
        public static TypEnum getByValue(String value) {
            for (TypEnum dataType : values()) {
                if (Objects.equals(dataType.getType(), value)) {
                    return dataType;
                }
            }
            return null;
        }

}
