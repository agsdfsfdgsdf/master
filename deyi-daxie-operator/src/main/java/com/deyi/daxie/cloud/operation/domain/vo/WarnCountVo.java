package com.deyi.daxie.cloud.operation.domain.vo;

import com.deyi.daxie.cloud.operation.base.BasePage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "报警数据统计")
public class WarnCountVo   extends BasePage {
    /**
     * 车号
     */
    private String deviceNum;
    /**
     * 报警类型
     */
    private String type;

    private Object[] deviceTime;

    private String startTime;

    private String endTime;

    private Integer mark;
}
