package com.deyi.daxie.cloud.operation.domain.vo;

import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * 
 *
 */
@Data
@Tag(name = "吊桥信息数据")
public class TcsWebsocketGetQdInfoVo extends BasePage {

    /**
     * 请求时间
     */
    private String time;

    /**
     * 发出请求的集卡号
     */
    private String truckNo;

    /**
     * 桥吊名称
     */
    private String resName;

    /**
     * 0 正常工作状态  1 桥吊不可用  2 桥吊关路信息
     */
    private Integer resState;
}