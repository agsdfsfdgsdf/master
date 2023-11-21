package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
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
@ApiModel(value = "感知决策数据", description = "")
public class ObstacleDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("主键自增")
    private Long id;

    @ExcelProperty("车号，TOS中车号 ")
    private String deviceNum;

    @ExcelProperty("感知障碍物1类型，龙门吊:R；桥吊：Q；集卡：T；小车：C；其他静态障碍物：E；其他动态障碍物：M---厂商 ")
    private String obstacleStatus;

    @ExcelProperty("感知障碍物1尺寸，长*宽*高 ")
    private String obstacleSize;

    @ExcelProperty("感知障碍物1_横坐标位置，感知障碍物1 X位置 ")
    private Double obstacleDistanceX;

    @ExcelProperty("感知障碍物1_纵坐标位置，感知障碍物1 Y位置 ")
    private Double obstacleDistanceY;

    @ExcelProperty("感知障碍物1横坐标速度，感知障碍物1 X速度 ")
    private Double obstacleSpeedX;

    @ExcelProperty("感知障碍物1纵坐标速度，感知障碍物1 Y速度 ")
    private Double obstacleSpeedY;

    @ExcelProperty("感知障碍物1碰撞时间，感知障碍物1碰撞时间 ")
    private Double obstacleCollision;

    @ExcelProperty("感知障碍物1置信度，感知障碍物1置信度 ")
    private Double obstacleSafelevel;

    @ExcelProperty("人工介入，人工介入 ")
    private Boolean artIntervention;

    @ExcelProperty("因车道线质量差无法准确识别，因车道线质量差无法准确识别0.00-1，数字越大表示质量越好 ")
    private Double lineBreak;

    @ExcelProperty("批次号，标记哪条数据为同一次上传")
    private Long batchNo;

}
