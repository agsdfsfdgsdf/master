package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@ApiModel(value = "报警统计", description = "")
public class WarnCountDto {
    @ExcelProperty("主键自增")
    private Long id;

    @ExcelProperty("集卡号")
    private String deviceNum;

    @ExcelProperty("时间")
    private String deviceTime;

    @ExcelProperty("报警级别")
    private String type;
    @ExcelProperty("报警类型")
    private String desc;
    @ExcelProperty("报警数量")
    private Map<String,Integer> count;
}
