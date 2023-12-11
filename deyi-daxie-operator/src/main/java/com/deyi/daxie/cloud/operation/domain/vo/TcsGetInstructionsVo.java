package com.deyi.daxie.cloud.operation.domain.vo;

import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * 
 * @TableName tcs_get_instructions
 */
@Data
@Tag(name = "最新指令数据")
public class TcsGetInstructionsVo  extends BasePage {
    /**
     * 发出请求的集卡号
     */
    private String truckNo;

    /**
     * 时间
     */
    private String time;
}