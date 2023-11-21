package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 自动驾驶数据
 *
 * @author Chen Xu
 * @date 2023/6/7
 */
@Data
public class AutoDrive {
    /**
     * 故障数量
     */
    private Integer faultCount;

    /**
     * 故障时长
     */
    private Long faultTime;
    /**
     * 接管次数
     */
    private Integer takeoversCount;

    /**
     * 接管时长
     */
    private Long takeoversTime;
}
