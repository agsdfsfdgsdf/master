package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.deyi.daxie.cloud.operation.domain.ObstacleData;

@Mapper
@Repository
public interface ObstacleDataMapper extends BaseMapper<ObstacleData> {
}
