package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.daxie.cloud.operation.domain.AligningData;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AligningDataMapper extends BaseMapper<AligningData> {

    /**
     * 根据时间区间获取对位数据
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 对位数据列表
     */
    List<AligningData> getByRange(@Param("startTime") Date startTime,@Param("endTime") Date endTime);
}
