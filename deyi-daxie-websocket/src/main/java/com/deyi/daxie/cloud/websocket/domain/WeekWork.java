package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 周作业量和作业效率
 *
 * @author Chen Xu
 * @date 2023/6/6
 */
@Data
public class WeekWork {

    /**
     * 日期
     */
    private String date;
    /**
     * 集装箱数
     */
    private Integer containerCount = 0;
    /**
     * 作业时间
     */
    private Long time = 0L;
    /**
     * 效率
     */
    private Float efficiency = 0F;
}
