package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deyi.daxie.cloud.operation.domain.VelWarnData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface VelWarnDataMapper extends BaseMapper<VelWarnData> {
}
