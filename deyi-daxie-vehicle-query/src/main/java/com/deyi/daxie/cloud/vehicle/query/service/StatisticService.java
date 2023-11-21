package com.deyi.daxie.cloud.vehicle.query.service;


import com.deyi.daxie.cloud.vehicle.query.vo.Statistic;

/**
 * Description: 车辆聚合数据接口
 * @date 2022/9/25
 * @author Huang ShuYing
 */
public interface StatisticService {

    /**
     * Description: 累计里程和时间
     * @param vin  车辆vin
     * @param distance  距离
     * @param duration 时间
     * @date 2022/10/17
     * @author Huang ShuYing
     */
    void save(String vin, double distance, long duration);

    /**
     * Description: 聚合统计
     * @param date 日期
     * @return Statistic
     * @date 2022/10/24
     * @author Huang ShuYing
     */
    Statistic total(String date);
}
