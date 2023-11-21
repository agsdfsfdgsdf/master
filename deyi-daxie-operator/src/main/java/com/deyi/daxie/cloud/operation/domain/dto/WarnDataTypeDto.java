package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WarnDataTypeDto {


    @ExcelProperty("主键自增")
    private Long id;

    @ExcelProperty("集卡号")
    private String deviceNum;

    @ExcelProperty("时间")
    private String deviceTime;

    @ExcelProperty("报警类型")
    private String type;

}
