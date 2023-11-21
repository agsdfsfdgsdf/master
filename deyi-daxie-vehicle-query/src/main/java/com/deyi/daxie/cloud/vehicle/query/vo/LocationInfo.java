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
public class LocationInfo implements Serializable {

    private static final long serialVersionUID = 3214543943335972832L;
    /**
     * Description: 车辆id
     * @date 2022/9/6
     */
    private String vin;

    /**
     * Description: 车辆id
     * @date 2022/9/6
     */
    private String plateNumber;

    /**
     * Description: 经度
     * @date 2022/9/6
     */
    private Double longitude;

    /**
     * Description: 纬度
     * @date 2022/9/6
     */
    private Double latitude;

    /**
     * Description: 速度
     * @date 2022/9/6
     */
    private Float speed;

    /**
     * Description: 方位，航向角
     * @date 2022/9/6
     */
    private Float heading;

    /**
     * Description: 海拔
     * @date 2022/9/6
     */
    private Float height;

    /**
     * Description: 时间戳 毫秒级
     * @date 2022/9/6
     */
    private Long time;


}
