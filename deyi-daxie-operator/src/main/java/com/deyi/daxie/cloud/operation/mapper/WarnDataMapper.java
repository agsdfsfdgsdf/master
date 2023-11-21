package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.daxie.cloud.operation.domain.WarnData;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface WarnDataMapper extends BaseMapper<WarnData> {

    /**
     * 获取指定时间区间的报警信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 报警信息列表
     */
    List<WarnData> getByRange(@Param("startTime") Date startTime,@Param("endTime")  Date endTime);
}
