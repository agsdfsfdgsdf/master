package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author cxb
 * @since 2023-05-30
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "最新指令信息", description = "")
public class TcsGetInstructionsDto {
    /**
     * 主键，自增
     */
    @ExcelProperty("id")
    private Long id;

    /**
     * 请求时间
     */
    @ExcelProperty("请求时间")
    private Date time;

    /**
     * 发出请求的集卡号
     */
    @ExcelProperty("集卡号")
    private String truckNo;

    /**
     * 返回数据
     */
    @ExcelProperty("返回数据")
    private String resData;
}
