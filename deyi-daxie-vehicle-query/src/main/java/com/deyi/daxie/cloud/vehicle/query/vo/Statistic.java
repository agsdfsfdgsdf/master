package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description: 位置信息实体类
 * @author Huang ShuYing
 * @date 2022/9/6
 */
@Getter
@Setter
@ToString
public class Statistic implements Serializable {


    private static final long serialVersionUID = -598859194451864417L;
    /**
     * Description: 车辆id
     * @date 2022/9/6
     */
    private String vin;

    /**
     * Description: 里程
     * @date 2022/9/6
     */
    private Double mileage;

    /**
     * Description: 时间
     * @date 2022/9/6
     */
    private float duration;


}
