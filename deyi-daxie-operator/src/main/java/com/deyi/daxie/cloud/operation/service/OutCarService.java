package com.deyi.daxie.cloud.operation.service;

import com.deyi.daxie.cloud.operation.domain.OutCar;

import java.util.List;

/**
* @author m1771
* @description 针对表【out_car】的数据库操作Service
* @createDate 2023-11-03 14:42:27
*/
public interface OutCarService {

    void save(List<OutCar> list);
}
