package com.deyi.daxie.cloud.vehicle.query.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description: 车辆信息实体类
 *
 * @author Huang ShuYing
 * @date 2022/9/25
 */
@Getter
@Setter
public class VehicleInfo implements Serializable {

    private static final long serialVersionUID = -4691764582735367617L;
    /**
     * Description: 车辆id
     * @date 2022/9/6
     */
    private String vin;

    /**
     * 集卡号
     */
    private String deviceNum;

    /**
     * Description: 车辆id
     * @date 2022/9/6
     */
    private String plateNumber;

    /**
     * Description: 所属组织
     * @date 2022/9/25
     */
    private  String company;

    /**
     * Description: 协议
     * @date 2022/9/25
     */
    private String protocol;

    /**
     * Description: 车辆类型
     * @date 2022/9/25
     */
    private String vehicleType;

    /**
     * Description: 车辆状态
     * @date 2022/9/25
     */
    private String vehicleStatus;

    /**
     * Description: 创建时间
     * @date 2022/9/25
     */
    private String createTime;

    /**
     * tsc密码
     */
    private String tscPwd;

}
