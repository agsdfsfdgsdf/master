package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 首页基础数据
 *
 * @author Chen Xu
 * @date 2023/6/7
 */
@Data
public class InfoMessage {
    /**
     * 集装箱个数
     */
    private Integer containerCount;
    /**
     * 时间
     */
    private String time;
    /**
     * 运行时长
     */
    private String runTime;
    /**
     * 较昨日新增时长
     */
    private String compareTime;
    /**
     * 较昨日新增里程
     */
    private String compareMileage;
    /**
     * 平均速度
     */
    private String avgSpeed;
    /**
     * 总里程
     */
    private String allMileage;
}
