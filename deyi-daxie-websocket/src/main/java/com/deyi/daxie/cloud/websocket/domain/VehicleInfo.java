package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 车辆详情
 *
 * @author Chen Xu
 * @date 2023/6/7
 */
@Data
public class VehicleInfo {
    /**
     * 在线车辆数
     */
    private Integer vehicleOnline;
    /**
     * 离线车辆数
     */
    private Integer vehicleOffline;
}
