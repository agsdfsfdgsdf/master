package com.deyi.daxie.cloud.operation.domain.vo;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "报警数据统计")
public class WarnCountVo {
    /**
     * 车号
     */
    private String deviceNum;
    /**
     * 报警级别
     */
    private String type;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 报警类型
     */
    private int mark;

}
