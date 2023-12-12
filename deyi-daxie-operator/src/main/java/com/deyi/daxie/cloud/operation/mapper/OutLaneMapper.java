package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deyi.daxie.cloud.operation.domain.OutLane;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutLaneMapper extends BaseMapper<OutLane> {
    void save(List<OutLane> ls);
}
