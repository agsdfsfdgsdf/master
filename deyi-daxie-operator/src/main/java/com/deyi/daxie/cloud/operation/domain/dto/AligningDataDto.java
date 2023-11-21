package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deyi.daxie.cloud.operation.convert.ControlModeConvert;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "对位数据", description = "")
public class AligningDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ExcelProperty("对位设备类型")
    private String containerDev;

    @ExcelProperty("时间戳 ")
    private String Timestamp;

    @ExcelProperty("设备号")
    @TableField(value = "DeviceNo")
    private String DeviceNo;

    @ExcelProperty("集卡号")
    @TableField(value = "TruckNo")
    private String TruckNo;

    @ExcelProperty("具体移动的物理值")
    private Integer containerNo;

    @ExcelProperty("移动的百分比")
    private Double Rate;

    @ExcelProperty(value = "操作模式", converter = ControlModeConvert.class)
    private Boolean ControlMode;

}
