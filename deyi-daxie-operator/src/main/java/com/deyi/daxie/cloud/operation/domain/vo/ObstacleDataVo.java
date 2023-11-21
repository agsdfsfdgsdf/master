package com.deyi.daxie.cloud.operation.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "感知决策数据")
public class ObstacleDataVo extends BasePage {


    @ApiModelProperty("主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("车号，TOS中车号 ")
    private String deviceNum;

    @ApiModelProperty("感知障碍物1类型，龙门吊:R；桥吊：Q；集卡：T；小车：C；其他静态障碍物：E；其他动态障碍物：M---厂商 ")
    private String obstacleStatus;

    @ApiModelProperty("感知障碍物1尺寸，长*宽*高 ")
    private String obstacleSize;

    @ApiModelProperty("感知障碍物1_横坐标位置，感知障碍物1 X位置 ")
    private Double obstacleDistanceX;

    @ApiModelProperty("感知障碍物1_纵坐标位置，感知障碍物1 Y位置 ")
    private Double obstacleDistanceY;

    @ApiModelProperty("感知障碍物1横坐标速度，感知障碍物1 X速度 ")
    private Double obstacleSpeedX;

    @ApiModelProperty("感知障碍物1纵坐标速度，感知障碍物1 Y速度 ")
    private Double obstacleSpeedY;

    @ApiModelProperty("感知障碍物1碰撞时间，感知障碍物1碰撞时间 ")
    private Double obstacleCollision;

    @ApiModelProperty("感知障碍物1置信度，感知障碍物1置信度 ")
    private Double obstacleSafelevel;

    @ApiModelProperty("人工介入，人工介入 ")
    private Boolean artIntervention;

    @ApiModelProperty("因车道线质量差无法准确识别，因车道线质量差无法准确识别0.00-1，数字越大表示质量越好 ")
    private Double lineBreak;

    @ApiModelProperty("批次号，标记哪条数据为同一次上传")
    private Long batchNo;
}
