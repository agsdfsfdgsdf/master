package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel(value = "作业数据", description = "")
public class MissionDataDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty("主键自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long tableId;

    @ExcelProperty("落箱距离误差值")
    private Integer containerDev;

    @ExcelProperty("接受指令时间")
    private String receivungTime;

    @ExcelProperty("指令完成时间")
    private String finishTime;

    @ExcelProperty("集卡号")
    private String deviceNum;

    @ExcelProperty("作业箱号")
    private String containerNo;

    @ExcelProperty("Toss任务ID")
    private Integer id;

}
