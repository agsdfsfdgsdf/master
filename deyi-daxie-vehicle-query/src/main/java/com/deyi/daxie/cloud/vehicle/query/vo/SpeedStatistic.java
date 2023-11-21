package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 速度统计
 * @author Huang ShuYing
 * @date 2022/9/6
 */
@Getter
@Setter
@ToString
public class SpeedStatistic implements Serializable {
    private static final long serialVersionUID = 3701034819849173665L;
    /**
     * Description: 里程
     * @date 2022/9/6
     */
    private int max;

    /**
     * Description: 时间
     * @date 2022/9/6
     */
    private int avg;

    /**
     * Description: 时间
     * @date 2022/9/6
     */
    private long time;

}
