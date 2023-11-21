package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deyi.daxie.cloud.operation.convert.EnergyConvert;
import com.deyi.daxie.cloud.operation.convert.LoginStatusConvert;
import com.deyi.daxie.cloud.operation.convert.OperationModeConvert;
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
@ApiModel(value = "车辆状态", description = "")
public class StatusDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ExcelProperty("车号")
    private String deviceNum;

    @ExcelProperty("所属车队")
    private String fleet;

    @ExcelProperty(value = "能源模式", converter = EnergyConvert.class)
    private String energy;

    @ExcelProperty(value = "登录状态", converter = LoginStatusConvert.class)
    private Integer loginStatus;

    @ExcelProperty(value = "作业模式", converter = OperationModeConvert.class)
    private Integer operationMode;

    @ExcelProperty("版本号")
    private String version;

    @ExcelProperty("时间")
    private String deviceTime;

}
