package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deyi.daxie.cloud.operation.convert.*;
import io.swagger.annotations.ApiModel;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "控制数据", description = "")
public class ControlDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("车号")
    private String deviceNum;

    @ExcelProperty("实际经度")
    private Double longitudeR;

    @ExcelProperty("实际纬度")
    private Double latitudeR;

    @ExcelProperty("规划经度")
    private Double longitudeD;

    @ExcelProperty("规划纬度")
    private Double latitudeD;

    @ExcelProperty("实际速度(km/h)")
    private Double speedR;

    @ExcelProperty("规划速度(km/h)")
    private Integer speedD;

    @ExcelProperty("区域限速(km/h)")
    private Integer speedL;

    @ExcelProperty("油门控制")
    private Double throttleR;

    @ExcelProperty("油门期望")
    private Double throttleD;

    @ExcelProperty("底层油门反馈量与决策目标量差值")
    private Double throttleS;

    @ExcelProperty("刹车控制")
    private Double brakeR;

    @ExcelProperty("刹车期望")
    private Double brakeD;

    @ExcelProperty("底层制动反馈量与决策目标量差值")
    private Double brakeS;

    @ExcelProperty("转向控制")
    private Double wheelR;

    @ExcelProperty("转向期望")
    private Double wheelD;

    @ExcelProperty("底层转向反馈信息与决策目标量差值")
    private Double wheelS;

    @ExcelProperty(value = "挡位控制", converter = GearConvert.class)
    private String gearR;

    @ExcelProperty(value = "挡位期望", converter = GearConvert.class)
    private String gearD;

    @ExcelProperty(value = "车灯控制", converter = LightConvert.class)
    private String lightR;

    @ExcelProperty(value = "车灯期望", converter = LightConvert.class)
    private String lightD;

    @ExcelProperty(value = "是否对位", converter = CpConvert.class)
    private Integer isCp;

    @ExcelProperty(value = "是否对位开始", converter = CpStatusConvert.class)
    private Integer cpStart;

    @ExcelProperty(value = "是否对位结束", converter = CpStatusConvert.class)
    private Integer cpEnd;

    @ExcelProperty("时间")
    private String deviceTime;

    @ExcelProperty(value = "所在位置", converter = PositionConvert.class)
    private Integer position;

    @ExcelProperty("车道中心线偏移")
    private Integer devDistance;

}
