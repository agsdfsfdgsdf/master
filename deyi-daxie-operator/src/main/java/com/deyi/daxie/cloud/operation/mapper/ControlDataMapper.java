package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.daxie.cloud.operation.domain.ControlData;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface ControlDataMapper extends BaseMapper<ControlData> {

    /**
     * 根据车号列表获取车辆位置最新信息集合
     * @param deviceNum 车号列表
     * @return 车辆位置信息集合
     */
    List<ControlData> getLocation(@Param("deviceNums") Set<String> deviceNum);

    /**
     * 获取指定时间区间的数据列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 数据列表
     */
    List<ControlData> getByRange(@Param("startTime") Date startTime,@Param("endTime")  Date endTime);
}
