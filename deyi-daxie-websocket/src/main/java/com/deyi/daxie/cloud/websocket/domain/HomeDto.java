package com.deyi.daxie.cloud.websocket.domain;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Description: 首页数据
 *
 * @author Chen Xu
 * @date 2023/6/6
 */
@Data
public class HomeDto {

    /**
     * 基础数据
     */
    private InfoMessage info;

    /**
     * 自动驾驶数据
     */
    private AutoDrive autoDrive;

    /**
     * 周作业量和作业效率
     */
    private List<WeekWork> weekWorks;

    /**
     * 车辆详情
     */
    private VehicleInfo vehicle;

    /**
     * 对位数据
     */
    private ParaPosition position;

    /**
     * 警报数量
     */
    private Map<String, Integer> warnCount;

}
