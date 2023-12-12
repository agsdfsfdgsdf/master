package com.deyi.daxie.cloud.operation.domain.dto;

import lombok.Data;

@Data
public class WarnDto {
    /**
     * 车号
     */
    private String deviceNum;
    /**
     * 报警类型
     */
    private String grade;
    /**
     * 报警数量
     */
    private Integer count;
}
