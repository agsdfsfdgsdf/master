package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.daxie.cloud.operation.domain.MissionData;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface MissionDataMapper extends BaseMapper<MissionData> {

    /**
     * 根据车号列表获取最新的箱号信息
     * @param deviceNum 车号列表
     * @return 最新的箱号信息
     */
    List<MissionData> getByDeviceNums(@Param("deviceNums") Set<String> deviceNum);

    /**
     * 获取时间区间内的数据列表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 数据列表
     */
    List<MissionData> selectByRange(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
