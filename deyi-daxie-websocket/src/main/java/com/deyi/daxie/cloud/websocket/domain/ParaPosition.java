package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

/**
 * Description: 对位数据
 *
 * @author Chen Xu
 * @date 2023/6/7
 */
@Data
public class ParaPosition {
    /**
     * 手工对位
     */
    private Long manualCount;
    /**
     * 自动对位
     */
    private Long automatic;
}
