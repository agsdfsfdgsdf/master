package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.deyi.daxie.cloud.operation.domain.StatusData;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface StatusDataMapper extends BaseMapper<StatusData> {


    /**
     * 根据车号列表获取对应车号最后一次信息
     * @param deviceNum 车号
     * @return 信息列表
     */
    List<StatusData> getStatusByList(@Param("deviceNums") Set<String> deviceNum);
}
