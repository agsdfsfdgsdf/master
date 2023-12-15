package com.deyi.daxie.cloud.operation.domain.vo;

import com.deyi.daxie.cloud.operation.base.BasePage;
import lombok.Data;

@Data
public class VideoParamVo extends BasePage {
    /**
     * 车号
     */
    private String deviceNum;
    /**
     * 报警类型
     */
    private String startTime;

    private String endTime;

    private String channel;

    private String url;

    private String savePath;
}
