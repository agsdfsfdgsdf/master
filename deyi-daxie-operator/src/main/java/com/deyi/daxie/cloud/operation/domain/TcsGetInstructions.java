package com.deyi.daxie.cloud.operation.domain;

import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @TableName tcs_get_instructions
 */
@Data
public class TcsGetInstructions extends BasePage {
    /**
     * 主键，自增
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 请求时间
     */
    @ApiModelProperty("请求时间")
    private Date time;

    /**
     * 发出请求的集卡号
     */
    @ApiModelProperty("发出请求的集卡号")
    private String truckNo;

    /**
     * 请求token
     */
    @ApiModelProperty("请求token")
    private String token;

    /**
     * 集卡号
     */
    @ApiModelProperty("集卡号")
    private String paramTruckno;

    /**
     * 返回状态码
     */
    @ApiModelProperty("返回状态码")
    private String resCode;

    /**
     * msg
     */
    @ApiModelProperty("msg")
    private String resMessage;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据")
    private String resData;

    private static final long serialVersionUID = 1L;
}