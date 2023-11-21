package com.deyi.daxie.cloud.operation.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

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
@TableName("vel_warn_data")
@ApiModel(value = "报警数据", description = "")
public class WarnData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("集卡号,TOS中车号 ")
    private String deviceNum;

    @ApiModelProperty("时间,yyyy-MM-dd HH:mm:ss ")
    private String deviceTime;

    @ApiModelProperty("灯光（左转，右转，双闪）")
    private Boolean warningTurnlight;

    @ApiModelProperty("喇叭")
    private Boolean warningSpeaker;

    @ApiModelProperty("胎压")
    private Boolean warningTirepress;

    @ApiModelProperty("安全带")
    private Boolean warningSeatbelt;

    @ApiModelProperty("拖挂灯控")
    private Boolean warningBacklight;

    @ApiModelProperty("拖挂气路")
    private Boolean warningPress;

    @ApiModelProperty("雨刷")
    private Boolean warningWiper;

    @ApiModelProperty("大灯")
    private Boolean warningFrontlight;

    @ApiModelProperty("LED屏")
    private Boolean warningLed;

    @ApiModelProperty("警示灯")
    private Boolean warningAlarm;

    @ApiModelProperty("无底层制动反馈信息")
    private Boolean warningBreak;

    @ApiModelProperty("无底层油门反馈信息")
    private Boolean warningAcc;

    @ApiModelProperty("无底层转角反馈信息")
    private Boolean warningTurn;

    @ApiModelProperty("激光雷达有无数据")
    private Boolean laserLag;

    @ApiModelProperty("激光雷达单帧点数异常")
    private Boolean singlelaser;

    @ApiModelProperty("激光雷达噪点异常")
    private Boolean multilaser;

    @ApiModelProperty("摄像头无数据")
    private Boolean cameradata;

    @ApiModelProperty("摄像头数据异常-画面静止")
    private Boolean cameraLag;

    @ApiModelProperty("毫米波雷达无数据")
    private Boolean mmradarLag;

    @ApiModelProperty("毫米波雷达数据异常-障碍物信息异常")
    private Boolean mmraderBug;

    @ApiModelProperty("轮速计无数据")
    private Boolean wheelspeedLag;

    @ApiModelProperty("轮速计标准差过大")
    private Boolean wheelspeedSd;

    @ApiModelProperty("转角传感器无数据")
    private Boolean anglearsensorLag;

    @ApiModelProperty("急刹")
    private Boolean warningHardbrake;

    @ApiModelProperty("长时间等待")
    private Boolean warningLwt;

    @ApiModelProperty("转角阈值")
    private Boolean turnMainstreet;

    @ApiModelProperty("转角阈值")
    private Boolean turnInbay;

    @ApiModelProperty("转角阈值")
    private Boolean turnDock;

    @ApiModelProperty("转角阈值")
    private Boolean turnCharge;

    @ApiModelProperty("转角阈值")
    private Boolean turnCurve;

    @ApiModelProperty("GNSS定位无数据")
    private Boolean gnssData;

    @ApiModelProperty("GNSS定位信号弱")
    private Boolean gnssLag;

    @ApiModelProperty("IMU无数据")
    private Boolean imuData;

    @ApiModelProperty("IMU帧间方差过大")
    private Boolean imuSd;

    @ApiModelProperty("融合定位误差过大")
    private Boolean locationSd;

}
