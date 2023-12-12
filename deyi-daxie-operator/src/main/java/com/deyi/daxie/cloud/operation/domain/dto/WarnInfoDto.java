package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WarnInfoDto {
    @ExcelProperty("主键自增")
    private Long id;
    @ExcelProperty("集卡号")
    private String deviceNum;
    @ExcelProperty("报警时间")
    private String deviceTime;
    @ExcelProperty("报警级别")
    private String level;
    @ExcelProperty("报警类型")
    private String type;
    @ExcelProperty("报警数量")
    private Integer count;
    @ExcelProperty("经度")
    private String lat;
    @ExcelProperty("纬度")
    private String lon;
}
