package com.deyi.daxie.cloud.operation.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * 
 * @TableName tcs_websocket_get_qd_info
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "吊桥信息", description = "")
public class TcsWebsocketGetQdInfoDto {
    /**
     * 主键，自增
     */
    @ExcelProperty("id")
    private Long id;

    /**
     * 请求时间
     */
    @ExcelProperty("请求时间")
    private String time;

    /**
     * 发出请求的集卡号
     */
    @ExcelProperty("集卡号")
    private String truckNo;

    /**
     * 变化的内容
     */
    @ExcelProperty("id")
    private Integer resChangedtype;

    /**
     * 桥吊id
     */
    @ExcelProperty("桥吊id")
    private String resId;

    /**
     * 桥吊名称
     */
    @ExcelProperty("桥吊名称")
    private String resName;

    /**
     * 0 正常工作状态  1 桥吊不可用  2 桥吊关路信息
     */
    @ExcelProperty("状态")
    private Integer resState;

    /**
     * S20 小，S40 40尺箱，S45 45尺箱， S220 双小箱
     */
    @ExcelProperty("尺箱")
    private String resSling;

    /**
     * GPS位置  预留
     */
    @ExcelProperty("GPS位置")
    private String resPosition;

    /**
     * 关路信息
     */
    @ExcelProperty("关路信息")
    private String resClosedlanes;

    /**
     * 刷新时间
     */
    @ExcelProperty("刷新时间")
    private String resRefreshtime;
    /**
     * 刷新时间
     */
    @ExcelProperty("刷新时间信息")
    private String resRefreshtimevalue;


}