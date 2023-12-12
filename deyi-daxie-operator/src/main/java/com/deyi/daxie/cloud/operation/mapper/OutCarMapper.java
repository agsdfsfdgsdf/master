package com.deyi.daxie.cloud.operation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deyi.daxie.cloud.operation.domain.OutCar;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
* @author m1771
* @description 针对表【out_car】的数据库操作Mapper
* @createDate 2023-11-03 14:42:27
*/
public interface OutCarMapper extends BaseMapper<OutCar> {

    void save(@Param("list") List<OutCar> list);
}




