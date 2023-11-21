package com.deyi.daxie.cloud.vehicle.query.mapper;

import com.deyi.daxie.cloud.vehicle.query.vo.Statistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
@Mapper
public interface StatisticMapper {

    /**
     * Description:
     * @param vin 车id
     * @return 个数
     * @date 2022/10/17
     * @author Huang ShuYing
     */
    int queryCount(@Param("vin") String vin);

    /**
     * Description:
     * @param vin 车id
     * @param distance 距离
     * @param duration 时间
     * @return 个数
     * @date 2022/10/17
     * @author Huang ShuYing
     */
    int update(@Param("vin") String vin, @Param("distance") double distance, @Param("duration") long duration);
    /**
     * Description:
     * @param vin 车id
     * @param distance 距离
     * @param duration 时间
     * @return 个数
     * @date 2022/10/17
     * @author Huang ShuYing
     */
    Integer add(@Param("vin") String vin, @Param("distance") double distance, @Param("duration") long duration);
    /**
     * Description:
     * @param date 日期
     * @return 对象
     * @date 2022/10/17
     * @author Huang ShuYing
     */
    Statistic total(@Param("date") String date);
}
