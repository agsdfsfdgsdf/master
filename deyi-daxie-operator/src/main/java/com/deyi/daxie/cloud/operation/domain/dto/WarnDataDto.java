package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cx
 * @since 2023-05-30
 */
@Data
@ToString
@ApiModel(value = "报警数据", description = "")
public class WarnDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ExcelProperty("集卡号,TOS中车号 ")
    private String deviceNum;

    @ExcelProperty("时间,yyyy-MM-dd HH:mm:ss ")
    private String deviceTime;

    @ExcelProperty("灯光（左转，右转，双闪）")
    private Boolean warningTurnlight;

    @ExcelProperty("喇叭")
    private Boolean warningSpeaker;

    @ExcelProperty("胎压")
    private Boolean warningTirepress;

    @ExcelProperty("安全带")
    private Boolean warningSeatbelt;

    @ExcelProperty("拖挂灯控")
    private Boolean warningBacklight;

    @ExcelProperty("拖挂气路")
    private Boolean warningPress;

    @ExcelProperty("雨刷")
    private Boolean warningWiper;

    @ExcelProperty("大灯")
    private Boolean warningFrontlight;

    @ExcelProperty("LED屏")
    private Boolean warningLed;

    @ExcelProperty("警示灯")
    private Boolean warningAlarm;

    @ExcelProperty("无底层制动反馈信息")
    private Boolean warningBreak;

    @ExcelProperty("无底层油门反馈信息")
    private Boolean warningAcc;

    @ExcelProperty("无底层转角反馈信息")
    private Boolean warningTurn;

    @ExcelProperty("激光雷达有无数据")
    private Boolean laserLag;

    @ExcelProperty("激光雷达单帧点数异常")
    private Boolean singlelaser;

    @ExcelProperty("激光雷达噪点异常")
    private Boolean multilaser;

    @ExcelProperty("摄像头无数据")
    private Boolean cameradata;

    @ExcelProperty("摄像头数据异常-画面静止")
    private Boolean cameraLag;

    @ExcelProperty("毫米波雷达无数据")
    private Boolean mmradarLag;

    @ExcelProperty("毫米波雷达数据异常-障碍物信息异常")
    private Boolean mmraderBug;

    @ExcelProperty("轮速计无数据")
    private Boolean wheelspeedLag;

    @ExcelProperty("轮速计标准差过大")
    private Boolean wheelspeedSd;

    @ExcelProperty("转角传感器无数据")
    private Boolean anglearsensorLag;

    @ExcelProperty("急刹")
    private Boolean warningHardbrake;

    @ExcelProperty("长时间等待")
    private Boolean warningLwt;

    @ExcelProperty("转角阈值")
    private Boolean turnMainstreet;

    @ExcelProperty("转角阈值")
    private Boolean turnInbay;

    @ExcelProperty("转角阈值")
    private Boolean turnDock;

    @ExcelProperty("转角阈值")
    private Boolean turnCharge;

    @ExcelProperty("转角阈值")
    private Boolean turnCurve;

    @ExcelProperty("GNSS定位无数据")
    private Boolean gnssData;

    @ExcelProperty("GNSS定位信号弱")
    private Boolean gnssLag;

    @ExcelProperty("IMU无数据")
    private Boolean imuData;

    @ExcelProperty("IMU帧间方差过大")
    private Boolean imuSd;

    @ExcelProperty("融合定位误差过大")
    private Boolean locationSd;

}
