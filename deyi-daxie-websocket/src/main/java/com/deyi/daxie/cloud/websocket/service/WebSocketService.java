package com.deyi.daxie.cloud.websocket.service;

import com.deyi.daxie.cloud.websocket.domain.ParamVo;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/5
 */
public interface WebSocketService {
    /**
     * 首页数据处理
     * @param paramVo paramVo
     */
    void home(ParamVo paramVo);

    /**
     * 位置监控数据处理
     * @param paramVo paramVo
     */
    void location(ParamVo paramVo);

    /**
     * 位置监控获取车辆列表
     * @param paramVo
     */
    void driverNum(ParamVo paramVo);

    /**
     * 强制下线
     * @param paramVo 参数信息
     */
    void offline(ParamVo paramVo);
}
