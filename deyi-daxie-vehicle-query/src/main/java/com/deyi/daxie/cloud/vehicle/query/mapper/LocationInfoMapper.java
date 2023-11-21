package com.deyi.daxie.cloud.vehicle.query.mapper;

import com.deyi.daxie.cloud.vehicle.query.vo.LocationInfo;
import com.deyi.daxie.cloud.vehicle.query.vo.SpeedStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Description:
 * @date 2022/9/25
 * @author Huang ShuYing
 */
@Mapper
public interface LocationInfoMapper {

    /**
     * Description: 车辆位置存储
     * @param list 位置集合
     * @return  int 处理结果
     */
    int add(List<LocationInfo> list);

    /**
     * Description:速度趋势数据
     * @param t1 开始时间
     * @param t2 结束时间
     * @param t3 结束时间
     * @return List<SpeedStatistic>
     * @date 2022/11/1
     * @author Huang ShuYing
     */
    List<SpeedStatistic> list(@Param("t1") long t1,@Param("t2") long t2,@Param("t3") long t3);
}
