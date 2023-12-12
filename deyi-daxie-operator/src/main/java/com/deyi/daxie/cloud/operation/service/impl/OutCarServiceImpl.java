package com.deyi.daxie.cloud.operation.service.impl;

import com.deyi.daxie.cloud.operation.domain.OutCar;
import com.deyi.daxie.cloud.operation.mapper.OutCarMapper;
import com.deyi.daxie.cloud.operation.service.OutCarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author m1771
* @description 针对表【out_car】的数据库操作Service实现
* @createDate 2023-11-03 14:42:27
*/
@Service
public class OutCarServiceImpl implements OutCarService {
    @Resource
    private OutCarMapper outCarMapper;

    @Override
    public void save(List<OutCar> list) {
            outCarMapper.save(list);
    }
}




