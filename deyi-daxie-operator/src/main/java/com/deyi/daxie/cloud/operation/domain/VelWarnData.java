package com.deyi.daxie.cloud.operation.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * @TableName warn_data
 */
@Getter
@Setter
@ToString
@Data
public class VelWarnData implements Serializable {
    /**
     * 主键自增
     */
    private Long id;

    /**
     * 集卡号,TOS中车号 
     */
    private String deviceNum;

    /**
     * 时间,yyyy-MM-dd HH:mm:ss 
     */
    private String deviceTime;

    /**
     * 灯光（左转，右转，双闪）
     */
    private Boolean warningTurnlight;

    /**
     * 喇叭
     */
    private Boolean warningSpeaker;

    /**
     * 胎压
     */
    private Boolean warningTirepress;

    /**
     * 安全带
     */
    private Boolean warningSeatbelt;

    /**
     * 拖挂灯控
     */
    private Boolean warningBacklight;

    /**
     * 拖挂气路
     */
    private Boolean warningPress;

    /**
     * 雨刷
     */
    private Boolean warningWiper;

    /**
     * 大灯
     */
    private Boolean warningFrontlight;

    /**
     * LED屏
     */
    private Boolean warningLed;

    /**
     * 警示灯
     */
    private Boolean warningAlarm;

    /**
     * 无底层制动反馈信息
     */
    private Boolean warningBreak;

    /**
     * 无底层油门反馈信息
     */
    private Boolean warningAcc;

    /**
     * 无底层转角反馈信息
     */
    private Boolean warningTurn;

    /**
     * 激光雷达有无数据
     */
    private Boolean laserLag;

    /**
     * 激光雷达单帧点数异常
     */
    private Boolean singlelaser;

    /**
     * 激光雷达噪点异常
     */
    private Boolean multilaser;

    /**
     * 摄像头无数据
     */
    private Boolean cameradata;

    /**
     * 摄像头数据异常-画面静止
     */
    private Boolean cameraLag;

    /**
     * 毫米波雷达无数据
     */
    private Boolean mmradarLag;

    /**
     * 毫米波雷达数据异常-障碍物信息异常
     */
    private Boolean mmraderBug;

    /**
     * 轮速计无数据
     */
    private Boolean wheelspeedLag;

    /**
     * 轮速计标准差过大
     */
    private Boolean wheelspeedSd;

    /**
     * 转角传感器无数据
     */
    private Boolean anglearsensorLag;

    /**
     * 急刹
     */
    private Boolean warningHardbrake;

    /**
     * 长时间等待
     */
    private Boolean warningLwt;

    /**
     * 转角阈值
     */
    private Boolean turnMainstreet;

    /**
     * 转角阈值
     */
    private Boolean turnInbay;

    /**
     * 转角阈值
     */
    private Boolean turnDock;

    /**
     * 转角阈值
     */
    private Boolean turnCharge;

    /**
     * 转角阈值
     */
    private Boolean turnCurve;

    /**
     * GNSS定位无数据
     */
    private Boolean gnssData;

    /**
     * GNSS定位信号弱
     */
    private Boolean gnssLag;

    /**
     * IMU无数据
     */
    private Boolean imuData;

    /**
     * IMU帧间方差过大
     */
    private Boolean imuSd;

    /**
     * 融合定位误差过大
     */
    private Boolean locationSd;

    private static final long serialVersionUID = 1L;

}